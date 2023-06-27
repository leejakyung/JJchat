package com.chatting.client;

import com.chatting.client.core.Client;
import com.chatting.client.view.MainView;


/**
 * 개별 뷰 테스트할때만 사용하도록
 */
public class ViewTestClass {

    public static void main(String[] args) {

        Client client = new Client("127.0.0.1", 9100);

//        LoginView loginView = new LoginView(client);
//        JoinView joinView = new JoinView();
        MainView mainView = new MainView(client);
//        CreateChattingView createChattingView = new CreateChattingView();
//        ChatRoomView chatRoomView = new ChatRoomView();
    }
}
