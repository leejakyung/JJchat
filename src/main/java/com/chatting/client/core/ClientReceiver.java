package com.chatting.client.core;

import com.chatting.client.model.Protocol;
import com.chatting.client.view.ChatListPanel;
import com.chatting.client.view.ChatRoomView;
import com.chatting.client.view.CreateChattingView;
import com.chatting.client.view.LoginView;
import com.chatting.client.view.MainView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientReceiver extends Thread {

	private static final Logger logger = LogManager.getLogger(ClientReceiver.class);

    private final Client client;
    private LoginView loginView;
    private MainView mainView;
    private List<String> targetIdList = new ArrayList<String>();
    private List<String> roomNameList = new ArrayList<String>(); 
    

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
                        
                        String onlineIdList =  arr[1].substring(0, arr[1].length());
                        
                        String[] online_id =  onlineIdList.replace("[","").replace("]","").split(",");
                        
                        List<String> loginId = new ArrayList<String>();
                        
                        for (String onID : online_id) {
							String user = onID;
							loginId.add(user);
						}
                        
                        mainView.changeOnlineUserList(loginId);
                        
                        logger.info("온라인 유저 : {}", online_id);
                        break;
                    case "121":
                        logger.info(arr[1]);
                        
                        String offlineIdList =  arr[1].substring(0, arr[1].length());
                        
                        String[] offline_id =  offlineIdList.replace("[","").replace("]","").split(",");
                        
                        List<String> logoutId = new ArrayList<String>();
                        
                        for (String offID : offline_id) {
							String user = offID;
							logoutId.add(user);
						}
                        mainView.changeOfflineUserList(logoutId);
                        
                        logger.info("오프라인 유저 : {}", offline_id);
                     
                        
                        break;
                        
                    case "200": // 채팅방 생성
                    	
                    	String myId = arr[1];
                    	String targetId = arr[2];
                    	String room = arr[3];
                    	
                    	if(!targetIdList.contains(targetId)) { // 새로운 채팅방 생성	
                    		if(!roomNameList.contains(room)) {
                    			targetIdList.add(targetId);
                    			roomNameList.add(room);                    		
                    			
                    			ChatRoomView chatRoomView = new ChatRoomView(client, myId, targetId, room);  
                    			chatRoomViewList.add(chatRoomView);		
	
                    		}
	
                    	} else { // 기존 채팅방 생성
                    		
                    	}      	
                    	
                    	break;
                    	
                    case "201": // 채팅 목록에서 채팅방 생성

                    	myId = arr[1];
                    	targetId = arr[2];
                    	room = arr[3];    
                    	
                    	
                    	// foreach 방법
                    	/*
                    	for (ChatRoomView chatRoom : chatRoomViewList) {
							if(chatRoom.getMyId().equals(myId) && chatRoom.getRoomName().equals(room)) {
								chatRoomViewList.remove(chatRoom);
								chatRoom = new ChatRoomView(client, myId, targetId, room);		
								chatRoomViewList.add(chatRoom);
								break;
							}
						}
						*/
                    	
                    	// iterator 방법
                    	
                    	Iterator<ChatRoomView> iterator = chatRoomViewList.iterator();
                    	
                    	while(iterator.hasNext()) {
                    		ChatRoomView chattingRoom = iterator.next();
                    		if(chattingRoom.getMyId().equals(myId) && chattingRoom.getRoomName().equals(room)) {
                    			// 아래 두줄 코드 동일하게 list의 next 값이 삭제 됨 
                    			// index와 차이가 있다면 원하는 인덱스번호에 값을 삭제하고 넣어주는게 아닌 리스트 값이 인덱스 번호 하나씩 앞으로 이동 
                    			iterator.remove();
//                    			chatRoomViewList.remove(chattingRoom);
                    			chattingRoom = new ChatRoomView(client, myId, targetId, room); 
                    			chatRoomViewList.add(chattingRoom);
                    			break;
                    		}                    		
              
                    	}
                    	
                    	
                    	
                    	// index를 이용한 방법
                    	/*
                    	for (int i = 0; i < chatRoomViewList.size(); i++) {
                    		ChatRoomView chatRoom = chatRoomViewList.get(i);
                    		if(chatRoom.getMyId().equals(myId) && chatRoom.getRoomName().equals(room)) {
                    			// 인덱스번호에 add하면 해당 자리에 덮어쓰기 되지만 이미 그 자리를 차지하고 있던 친구가 없어지는 것이 아닌 인덱스 마지막자리로 이동하므로 삭제 해준 뒤  추가해줘야 함
                    			// 예를 들어 리스트 안에 1,2,3 값이 들어 있는데 0번 인덱스 자리에 4를 add해주면  4,2,3,1 이런식으로 자리가 변경 됨 
                    			// 그러므로 해당 인덱스번호의 값을 완전히 삭제하고 싶다면 0번 인덱스를 삭제 해준 뒤 추가 해주면 4,2,3 이렇게 리스트에 값이 저장되는 것을 확인할 수 있음!!!
                    			chatRoomViewList.remove(i);
                    			chatRoom = new ChatRoomView(client, myId, targetId, room);
                    			chatRoomViewList.add(i, chatRoom);
                    			break;
                    		}

                    	}
                    	*/
                    	
                    	
                    	break;	
                    
                    case "202": // 채팅방 목록 
                    	
                    	myId = arr[1];
                    	targetId = arr[2];
                    	room = arr[3];       
                    	
                    	for (ChatRoomView chatRoom : chatRoomViewList) {
							if(chatRoom.getMyId().equals(myId)) {
								if(!roomNameList.contains(room)) {
		                    		roomNameList.add(room);     		
		                    	} 
								mainView.changeChatRoomList(roomNameList);
							}						
						}                    	                    	
                    	

                    	break;
                    	
                    case "300": // 메세지내용 전송

                    	myId = arr[1];
                    	targetId = arr[2];
                    	room = arr[3];
                    	String message = arr[4];
                    	
                    	
                    	for (ChatRoomView chatRoom : chatRoomViewList) {			
                    		if (chatRoom.getRoomName().equals(room)) {		
                    			logger.info("여기찍히나 ...?");
                    			logger.info("타겟아이디 :"  + targetId);
                    			logger.info("메세지 내용 찍히는 거 확인 : " + message);
                    			logger.info("받은 메세지 주소: {} " , System.identityHashCode(chatRoom));
                    			chatRoom.getSd_display().insertString(chatRoom.getSd_display().getLength(), "<"+ targetId +">" + message +"\n", null);
                    		}
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
            mainView = new MainView(client, id, chatRoomViewList);
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
