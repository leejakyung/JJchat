package com.chatting.client;

import com.chatting.client.model.Client1;

public class JjchatApplication {

	public static void main(String[] args) {
		String ip = "100.100.100.56";
		int port = 9100;
		new Client1(ip, port);
	}

}
