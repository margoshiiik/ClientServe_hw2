package network;


import makingInfo.Message;
import makingInfo.Packet;

import java.util.Random;

enum cTypes {
    GET_PRODUCT_COUNT,
    GET_PRODUCT,
    ADD_PRODUCT,
    ADD_PRODUCT_TITLE,
    SET_PRODUCT_PRICE,
    ADD_PRODUCT_TO_GROUP
}

//GENERATE MESSAGE FROM CLIENT

public class MessageGeneration {
    public static byte[] generate() {
        Random random = new Random();
        int command = random.nextInt(cTypes.values().length);
        String commandMsg = (cTypes.values()[command]).toString();
        Message testMessage = new Message(command,1, commandMsg);
        long bPktId = random.nextLong();
        Packet packet = new Packet((byte)1, bPktId, testMessage);
        byte[] packetToBytes = packet.toPacket();
        return packetToBytes;//encoded packet
    }
}