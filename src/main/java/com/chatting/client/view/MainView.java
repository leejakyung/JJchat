package com.chatting.client.view;


import com.chatting.client.model.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MainView extends JFrame {
    private static final Logger logger = LogManager.getLogger(MainView.class);

    private UserListPanel panel_userList;
    private ChatListPanel panel_chatList;

    public MainView() {
        initializeDisplay();
    }

    private void initializeDisplay() {

        JTabbedPane tabbedPane = new JTabbedPane();

//        this.addWindowListener(defHandler);
        panel_userList = new UserListPanel();
        panel_userList.add(new JLabel("유저목록", SwingConstants.CENTER));
        tabbedPane.addTab("유저목록", panel_userList);

        panel_chatList = new ChatListPanel();
        panel_chatList.add(new JLabel("방목록", SwingConstants.CENTER));
        tabbedPane.addTab("방목록", panel_chatList);

        this.setTitle(Protocol.myID);
        this.setBounds(650, 200, 500, 600);
        this.setVisible(true);
        this.add(tabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void changeUserList() {

           /*
          dtm_online.removeRow() 행 삭제
          dtm_online.addRow() 행 추가
	        for(Object obj:offlineUser) {
						Vector<Object> oneRow = new Vector<Object>();
						oneRow.add(obj);
						defView.dtm_offline.addRow(oneRow);
					}
           */
        DefaultTableModel dtm_online = panel_userList.getDtm_online();
        int onlineRowCount = dtm_online.getRowCount();
        logger.info("online user count : {}", onlineRowCount);

        DefaultTableModel dtm_offline = panel_userList.getDtm_offline();
        int offlineRowCount = dtm_offline.getRowCount();
        logger.info("offline user count : {}", offlineRowCount);

    }

    public void changeChatRoomList() {
        DefaultTableModel dtm_room = panel_chatList.getDtm_room();
        int rowCount = dtm_room.getRowCount();
        logger.info("chatList count : {}", rowCount);
    }

}
