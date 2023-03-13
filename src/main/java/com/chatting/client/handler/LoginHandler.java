package com.chatting.client.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.chatting.client.model.Protocol;
import com.chatting.client.view.LoginView;

public class LoginHandler implements ActionListener{
	private LoginView logView = null;

	public LoginHandler(LoginView logView){
		this.logView = logView;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		try {
			if (obj == logView.getButton_login() || obj == logView.getField_pw()) {



			} else if (obj == logView.getButton_join()) {


			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
