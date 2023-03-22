package com.chatting.client.model;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chatting.client.handler.LoginHandler;
import com.chatting.client.view.LoginView;
import com.chatting.client.view.MainView;

public class ClientMessageListener extends Thread {
	
	private static final Logger logger = LogManager.getLogger(ClientMessageListener.class);

    private Client client;
    private LoginView loginView = null;
    
    public ClientMessageListener(Client client){
        this.client = client;
     }

    public ClientMessageListener(Client client, LoginView loginView){
      this.client = client;
      this.loginView = loginView;
    }

    @Override
    public void run() {

        boolean isStop = false;
        while (client.isAlive()) {
            try {
                String msg = client.getOis().readObject().toString();
                logger.info(msg);
                logger.info(client);
                logger.info(loginView);
                if("로그인 성공".equals(msg)){
					MainView mainView = new MainView();
					if(loginView != null) {
                        loginView.dispose();
					}
				} else if("로그인 실패".equals(msg)) {
					JOptionPane.showMessageDialog(loginView, "로그인에 실패했습니다.");
				}

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
