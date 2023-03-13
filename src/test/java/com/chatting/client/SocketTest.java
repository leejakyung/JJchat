package com.chatting.client;

import com.chatting.client.model.Client1;
import org.junit.Test;

import java.net.Socket;

public class SocketTest {

    @Test
    void socketTest1() {
        Client1 client1 = new Client1("100.100.100.56", 9100);
    }
}
