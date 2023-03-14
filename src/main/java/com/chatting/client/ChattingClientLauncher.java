package com.chatting.client;

import com.chatting.client.model.Client;
import com.chatting.client.model.ClientMessageListener;

import java.io.IOException;

public class ChattingClientLauncher {
	
	public static final String ip = "192.168.0.5";
	
    public static void main(String[] args) {
        Client client = new Client(ip, 9100);
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
