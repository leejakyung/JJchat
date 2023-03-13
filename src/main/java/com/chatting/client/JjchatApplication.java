package com.chatting.client;

import com.chatting.client.model.Client1;

public class JjchatApplication {

	public static void main(String[] args) {
		String ip = "연결할 주소(ip)";
		int port = 9100;
		new Client1(ip, port);
	}

}
