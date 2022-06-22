package makingInfo;

import lombok.Data;

import java.nio.ByteBuffer;

@Data
public class Message {

    int cType;
    Integer bUserId;
    String message;

    public static final int BYTES_WITHOUT_MESSAGE = 8;

    public Message() { }

    public Message(Integer cType, Integer bUserId, String message) {
        this.cType = cType;
        this.bUserId = bUserId;
        this.message = message;
    }

    public byte[] toPacketPart() {
        return ByteBuffer.allocate(getMessageBytesLength())
                .putInt(cType)
                .putInt(bUserId)
                .put(message.getBytes()).array();
    }

    public int getMessageBytesLength() {
        return BYTES_WITHOUT_MESSAGE + getMessageBytes();
    }

    public Integer getMessageBytes() {
        return message.length();
    }

    public Integer getbUserId() {
        return bUserId;
    }

    public Integer getcType() {
        return cType;
    }


    public void encode() {
        message = Cipher.encode(message);
    }

    public void decode() {
        message = Cipher.decode(message);
    }

}