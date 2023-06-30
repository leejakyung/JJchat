package com.chatting.client.core;

import com.chatting.client.model.Protocol;
import com.chatting.client.view.ChatRoomView;
import com.chatting.client.view.LoginView;
import com.chatting.client.view.MainView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClientReceiver extends Thread {

	private static final Logger logger = LogManager.getLogger(ClientReceiver.class);

    private final Client client;
    private LoginView loginView;
    private MainView mainView;

    private final List<ChatRoomView> chatRoomViewList = new ArrayList<>();

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

                        String id =  arr[1];
                        if("Y".equals(arr[2])){
                            createMainView(client, id);
                            if(loginView != null) {
                                loginView.dispose();
                            }
                        } else if(!"Y".equals(arr[2])) {
                            JOptionPane.showMessageDialog(loginView, "로그인에 실패했습니다.");
                        }


                        break;
                    case "120":
                        logger.info(arr[1]);
                        
                        logger.info("온라인리스트 프로토콜");
                        break;
                    case "121":
                        logger.info(arr[1]);
                        logger.info("오프라인리스트 프로토콜");
                        String test = arr[1];
                        
                        List<String> offlineList = new ArrayList<String>();
                        
                        offlineList.add(test);
                        
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

    private synchronized void shutdown(){
        loginView = null;
        mainView = null;
        client.shutdown();
    }

}
