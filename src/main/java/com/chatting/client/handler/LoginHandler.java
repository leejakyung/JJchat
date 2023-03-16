package com.chatting.client.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chatting.client.ChattingClientLauncher;
import com.chatting.client.model.Client;
import com.chatting.client.model.ClientMessageListener;
import com.chatting.client.model.Protocol;
import com.chatting.client.view.LoginView;
import com.chatting.client.view.MainView;

public class LoginHandler implements ActionListener{

	private static final Logger logger = LogManager.getLogger(LoginHandler.class);

	private LoginView logView = null;
	private Client client;

//    private JTextField field_id = new JTextField();
//    private JPasswordField field_pw = new JPasswordField();
	
	public LoginHandler(LoginView logView, Client client){
		this.logView = logView;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		try {
//			logger.info("log4j 테스트!!!");
			if (obj == logView.getButton_login() || obj == logView.getField_pw()) {
				if (ae.getActionCommand().equals("로그인")) {
					if (client == null) {
						client = new Client(ChattingClientLauncher.ip, 9100);
						ClientMessageListener listener = new ClientMessageListener(client);
						listener.start();

					}
					
				

					String s = "로그인 버튼 클릭";
					try {
						client.getOos().writeObject(s);
						client.getOos().writeObject(Protocol.checkLogin+" "+logView.field_id.getText()+" "+logView.field_pw.getText());
						logger.info("100"+" "+logView.field_id.getText()+" "+logView.field_pw.getText());
						
						

					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}


				} 


			} else if (obj == logView.getButton_join()) {
				
				if (ae.getActionCommand().equals("회원가입")) {
					if (client == null) {
						client = new Client(ChattingClientLauncher.ip, 9100);
						ClientMessageListener listener = new ClientMessageListener(client);
						listener.start();

					} 

					String s = "회원가입 버튼 클릭";
					try {
						client.getOos().writeObject(s);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}


				} 


			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
