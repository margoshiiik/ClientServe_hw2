package makingInfo;

import com.github.snksoft.crc.CRC;
import lombok.Data;

import java.nio.ByteBuffer;

@Data
public class Packet {
    public final static Byte bMagic = 0x13;

    Byte bSrc;
    Long bPktId;
    Integer wLen;
    Message bMsq;
    Short wCrc16_1;
    Short wCrc16_2;

    public Packet(Byte bSrc, Long bPktId, Message bMsq) {
        this.bSrc = bSrc;
        this.bPktId = bPktId;

        this.bMsq = bMsq;
        wLen = bMsq.getMessage().length();
    }

    public Packet(byte[] bytes) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        Byte expectedBMagic = byteBuffer.get();
        System.out.println(bMagic);
        if (!expectedBMagic.equals(bMagic))
            throw new Exception("Incorrect first byte");

        bSrc = byteBuffer.get();
        bPktId = byteBuffer.getLong();
        wLen = byteBuffer.getInt();

        wCrc16_1 = byteBuffer.getShort();

        bMsq = new Message();
        bMsq.setCType(byteBuffer.getInt());
        bMsq.setBUserId(byteBuffer.getInt());
        byte[] messageBody = new byte[wLen];
        byteBuffer.get(messageBody);
        bMsq.setMessage(new String(messageBody));
        bMsq.decode();

        wCrc16_2 = byteBuffer.getShort();
    }

    public byte[] toPacket() {
        Message message = getBMsq();

        message.encode();

        Integer packetPartFirstLength = bMagic.BYTES + bSrc.BYTES + Long.BYTES + wLen.BYTES;
        byte[] packetPartFirst = ByteBuffer.allocate(packetPartFirstLength)
                .put(bMagic)
                .put(bSrc)
                .putLong(bPktId)
                .putInt(wLen)
                .array();

        wCrc16_1 = (short) CRC.calculateCRC(CRC.Parameters.CRC16, packetPartFirst);

        Integer packetPartSecondLength = message.getMessageBytesLength();
        byte[] packetPartSecond = ByteBuffer.allocate(packetPartSecondLength)
                .put(message.toPacketPart())
                .array();

        wCrc16_2 = (short) CRC.calculateCRC(CRC.Parameters.CRC16, packetPartSecond);

        Integer packetLength = packetPartFirstLength + wCrc16_1.BYTES + packetPartSecondLength + wCrc16_2.BYTES;

        return ByteBuffer.allocate(packetLength).put(packetPartFirst).putShort(wCrc16_1).put(packetPartSecond).putShort(wCrc16_2).array();
    }

    public Long getbPktId() {
        return bPktId;
    }

}