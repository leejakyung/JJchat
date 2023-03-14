package com.chatting.client;

import com.chatting.client.model.Client;
import com.chatting.client.model.ClientMessageListener;

import java.io.IOException;

public class ChattingClientLauncher {
    public static void main(String[] args) {
        Client client = new Client("100.100.100.56", 9100);
        ClientMessageListener listener = new ClientMessageListener(client);
        listener.start();

        String s = "테스트 진행";
        try {
            client.getOos().writeObject(s);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
