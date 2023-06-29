package com.chatting.client.core;

import com.chatting.client.model.Protocol;
import com.chatting.client.view.LoginView;
import com.chatting.client.view.MainView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

                        if("Y".equals(arr[2])){
                            createMainView(client);
                            if(loginView != null) {
                                loginView.dispose();
                            }
                        } else if(!"Y".equals(msg)) {
                            JOptionPane.showMessageDialog(loginView, "로그인에 실패했습니다.");
                        }


                        break;
                    case "120":
                        logger.info(arr[2]);

                        logger.info("유저리스트 프로토콜");
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

    private synchronized void createMainView(Client client){
        if(mainView == null){
            mainView = new MainView(client);
        }else{
            // 화면 내렸을때 다시 보여주는기능 찾아서 넣어도 됨
        }
    }

    private synchronized void shutdown(){
        loginView = null;
        mainView = null;
        client.shutdown();
    }

}
