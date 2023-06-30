package com.chatting.client;

import com.chatting.client.core.Client;
import com.chatting.client.view.ChatRoomView;
import com.chatting.client.view.CreateChattingView;
import com.chatting.client.view.MainView;

import java.util.Arrays;
import java.util.List;


/**
 * 개별 뷰 테스트할때만 사용하도록
 */
public class ViewTestClass {

    public static void main(String[] args) {
//        MainView mainView = new MainView(null);
//        List<String> online = Arrays.asList("A", "B", "C");
//        List<String> offline = Arrays.asList("D", "E", "F");
//        mainView.changeUserList(online, offline);

//        CreateChattingView view = new CreateChattingView();
        ChatRoomView view = new ChatRoomView();
    }
}
