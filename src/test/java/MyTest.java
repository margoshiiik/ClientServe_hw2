import makingInfo.Message;
import makingInfo.Packet;
import network.Processor;
import network.myNetwork;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTest {

    @Test
    void checkEncryption() {
        Message testMessage = new Message(1,1, new String("hello from user"));

        Packet packet = new Packet((byte) 1, (long) 4, testMessage);
        byte[] encodedPacket = packet.toPacket();
        try {
            Packet decodedPacket = new Packet(encodedPacket);
            byte[] decPacket = decodedPacket.toPacket();
            assertEquals(packet.getBSrc(), decodedPacket.getBSrc());
            assertEquals(packet.getWCrc16_1(), decodedPacket.getWCrc16_1());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkDecriptionForPacket() throws Exception {
        Message originalMessage = new Message(1,1, "hello from user");
        byte[] origMessageToBytes = originalMessage.toPacketPart();
        Packet packet = new Packet((byte)1, (long) 444, originalMessage);
        byte[] packetToBytes = packet.toPacket();//encoding packet
        Packet decoded_packet = new Packet(packetToBytes);
        Message decoded_message = decoded_packet.getBMsq();
        byte[] decoded_messageToBytes = decoded_message.toPacketPart();
        assert(Arrays.equals(origMessageToBytes, decoded_messageToBytes));
    }

    @Test
    void check_DeEncriptor_forPacket2() throws Exception {
        Message originalMessage = new Message(1,1, "la la la slava Ukraine");
        Packet packet = new Packet((byte)1, (long) 1111, originalMessage);
        byte[] packBytes = packet.toPacket();
        Packet packet1 = new Packet(packBytes);
        byte [] packBytes1 = packet1.toPacket();
        assert(Arrays.equals(packBytes, packBytes1));
    }

    @Test
    void checkWhether_SuccessfulFinished() {
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        for(int i = 0; i < 24; i++)
            executorService.submit(()->{
                myNetwork tcpNetwork = new myNetwork();
                tcpNetwork.receiveMessage();
            });
        try{
            executorService.shutdown();
            while(!executorService.awaitTermination(24L, TimeUnit.HOURS)){
                System.out.println("waiting for termination...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Processor.shutdown();
        System.out.println("End of main");
    }
}
