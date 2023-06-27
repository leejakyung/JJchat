package com.chatting.client;

import com.chatting.client.core.Client;
import com.chatting.client.view.LoginView;

public class ChattingClientLauncher {

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 9100);
    }
}
