package com.chatting.client.view;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chatting.client.core.Client;
import com.chatting.client.model.Protocol;

public class ChatListPanel extends JPanel {
	
	private static final Logger logger = LogManager.getLogger(ChatListPanel.class);
	
    private DefaultTableModel dtm_room;
    private String userName;
    private Client client;
    private List<ChatRoomView> chatRoomViewList;
    private String roomName;

    public ChatListPanel(String userName) {
        this.userName = userName;
        initializeDisplay();
        initialize();
    }
    
    public ChatListPanel(Client client, String userName, List<ChatRoomView> chatRoomViewList) {
        this.client = client;
        this.userName = userName;
        this.chatRoomViewList = chatRoomViewList;
        initializeDisplay();
        initialize();
    }


    public void initialize() {

    }

    public void initializeDisplay() {

        JPanel panel_chatList = new JPanel();
        panel_chatList.setBounds(0, 60, 500, 400);

        dtm_room = new DefaultTableModel(new String[]{"채팅방 목록"}, 0) {
            @Override //셀 더블클릭 후 수정 안되도록 조정.
            public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
                return false;
            }
        };
        JTable jtb_room = new JTable(dtm_room);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
        dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
        TableColumnModel tcm = jtb_room.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
        for (int i = 0; i < tcm.getColumnCount(); i++) {
            tcm.getColumn(i).setCellRenderer(dtcr);
        }
        JScrollPane jsp_room = new JScrollPane(jtb_room);
        panel_chatList.add(new JLabel("방목록"));
        panel_chatList.add(jsp_room);
        add(panel_chatList);

        
        JPanel panel_south = new JPanel();
        panel_south.setBounds(0, 500, 500, 40);
        JButton button_enter = new JButton("입장하기");
        button_enter.addActionListener(e ->{
        	int row = jtb_room.getSelectedRow();
            int col = jtb_room.getSelectedColumn();
            String cellData = getCellData(row, col);
        	for (ChatRoomView chatRoomView : chatRoomViewList) {
				if(chatRoomView.getRoomName().equals(cellData)) {
					client.sendMessage(Protocol.createRoomView, userName, chatRoomView.getTargetId(), chatRoomView.getRoomName());				
				}
			}
        });
//            button_enter.addActionListener(defHandler);
        panel_south.add(button_enter);
        add(panel_south);
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        setLayout(null);
    }


    public DefaultTableModel getDtm_room() {
        return dtm_room;
    }
    
    private String getCellData(int row, int col){
        return (String) dtm_room.getValueAt(row, col);
    }

}
