package network;//PACKS ENCODED RESPONSE FOR CLIENT


import makingInfo.Message;
import makingInfo.Packet;

public class Sender {
    public static byte[] packResponse(Packet packet) {
        String response = "Ok!";
        Message answer = new Message(packet.getBMsq().getcType(), packet.getBMsq().getbUserId(), response);
        Packet packetRespond = new Packet((byte) 1, packet.getbPktId(), answer);
        byte[] encodedPacket = packetRespond.toPacket();
        return encodedPacket;
    }
}