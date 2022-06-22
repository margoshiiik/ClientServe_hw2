package network;

import makingInfo.Message;
import makingInfo.Packet;

public class myNetwork implements Network{

    @Override
    public void receiveMessage() {
        try {
            Processor.process(MessageGeneration.generate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(byte[] message) {
        try {
            Packet packet = new Packet(message);
            Message message1 = packet.getBMsq();
            System.out.println("\n______________\n#" + Thread.currentThread().getId() + " Send respond to user: "
                    +  packet.getBMsq().getMessage() + "\nCommand: " + cTypes.values()[message1.getcType()]  + "\n______________");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
