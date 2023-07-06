package com.chatting.client.view;

import com.chatting.client.core.Client;
import com.chatting.client.model.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class CreateChattingView extends JFrame {

    private static final Logger logger = LogManager.getLogger(CreateChattingView.class);

    private Client client;
    private String myId;
    private String targetId;


    public CreateChattingView() {
        initializeDisplay();
    }
    
    public CreateChattingView(Client client) {
        this.client = client;
        initializeDisplay();
    }
    
    public CreateChattingView(String myId, String targetId) {
        this.myId = myId;
        this.targetId = targetId;
        initializeDisplay();
    }


    public CreateChattingView(Client client, String myId, String targetId) {
        this.client = client;
        this.myId = myId;
        this.targetId = targetId;
        initializeDisplay();
    }

    //화면처리부
    private void initializeDisplay() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        //////상단
        JPanel jp_north = new JPanel();
        jp_north.setBackground(Color.WHITE);

        Font font = new Font("고딕체", Font.BOLD, 12);
        JLabel jlb_title = new JLabel("채팅방 생성");
        jlb_title.setBounds(55, 200, 80, 40);
        jp_north.add(jlb_title);

        JPanel jp_center = new JPanel();
        ///////중단
        JLabel jlb_idName = new JLabel("채팅 대상 : ");
        jlb_idName.setBounds(40, 30, 80, 20);
        jp_center.add(jlb_idName);

        JLabel jlb_targetId = new JLabel(targetId);
        jlb_targetId.setBounds(120, 30, 100, 20);
        jp_center.add(jlb_targetId);

        JLabel jlb_roomName = new JLabel("채팅방 이름 : ");
        jlb_roomName.setBounds(40, 50, 80, 20);
        jp_center.add(jlb_roomName);

        JTextField jtf_roomName = new JTextField();
        jtf_roomName.setBounds(120, 50, 100, 20);
        jp_center.add(jtf_roomName);

        jp_center.setBackground(Color.WHITE);
        jp_center.setLayout(null);

        ///////하단
        JPanel jp_south = new JPanel();
        JButton jbtn_create = new JButton("만들기");
        logger.info("client: " + client);
		jbtn_create.addActionListener(e -> {
//            Protocol.createRoom + ~~~
            client.sendMessage(Protocol.createRoom, myId, jlb_targetId.getText());
            jtf_roomName.getText();
            this.dispose();
        });
        jp_south.add(jbtn_create);
        
        

        this.add("North", jp_north);
        this.add("Center", jp_center);
        this.add("South", jp_south);
        //////
        setTitle("채팅방 생성");
        setBounds(650, 200, 300, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
