package com.chatting.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chatting.client.core.Client;
import com.chatting.client.model.Protocol;

import javax.swing.*;


public class MyActionListener implements ActionListener {
	
	private static final Logger logger = LogManager.getLogger(MyActionListener.class);

	private Client client;
	private String myId;
	private String targetId;
	private JTextField roomName;
	private CreateChattingView createChattingView;
	
	public MyActionListener() {

    }

	public MyActionListener(Client client, String myId, String targetId) {
        this.client = client;
        this.myId = myId;
        this.targetId = targetId;
    }

	
	public MyActionListener(Client client, String myId, String targetId, JTextField roomName, CreateChattingView createChattingView) {
        this.client = client;
        this.myId = myId;
        this.targetId = targetId;
        this.roomName = roomName;
        this.createChattingView = createChattingView;
    }


	@Override
	public void actionPerformed(ActionEvent e) {

		logger.info("2");
		client.sendMessage(Protocol.createRoom, myId, targetId, roomName.getText());
		if(createChattingView != null) {
			createChattingView.dispose();
		}

		
	}


	
	

}
