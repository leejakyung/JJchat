package com.chatting.client.core;

import com.chatting.client.model.Protocol;
import com.chatting.client.view.LoginView;
import com.chatting.client.view.MainView;
import com.chatting.client.view.UserListPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import javax.swing.*;

public class ClientReceiver extends Thread {
	
	private static final Logger logger = LogManager.getLogger(ClientReceiver.class);

    private final Client client;
    private LoginView loginView;
    private MainView mainView;

    public ClientReceiver(Client client){
        this.client = client;
        createLoginView(client);
     }


    @Override
    public void run() {

        while (client.isAlive()) {
            try {
                String msg = client.getOis().readObject().toString();
                logger.info(msg);

                String[] arr = msg.split(Protocol.seperator);

                logger.info(arr);

                switch (arr[0]) {
                    case "100":
                        logger.info("로그인 프로토콜");
                        
                        msg = arr[1];
                        String id =  arr[2];
                        if("Y".equals(msg)){
                            createMainView(client, id);
                            if(loginView != null) {
                                loginView.dispose();
                            }
                        } else if("N".equals(msg)) {
                            JOptionPane.showMessageDialog(loginView, "로그인에 실패했습니다.");
                        }


                        break;
                    case "120":
                        logger.info("유저리스트 프로토콜");
                        
                        msg = arr[1];
                        
                        if("in".equals(msg)) {
//                        	List<String> loginUserList = 
                        } else if ("out".equals(msg)) {
                        	
                        }
                        
                        break;

                    default:
                        logger.info("Other case");
                }


            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }


	private synchronized void createLoginView(Client client){
        if(loginView == null){
            loginView = new LoginView(client);
        }
    }

    private synchronized void createMainView(Client client, String id){
        if(mainView == null){
            mainView = new MainView(client, id);
        }else{
            // 화면 내렸을때 다시 보여주는기능 찾아서 넣어도 됨
        }
    }
}
