package network;

public interface Network {
        void receiveMessage();
        void sendMessage(byte[] mess) throws Exception;
}

