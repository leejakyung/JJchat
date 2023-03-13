package com.chatting.client.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class CreateChattingView extends JFrame {
    //선언부
    private List<String> selected_ID = new Vector<>();
    private String roomName = null;

    //서버에 저장된 채팅방이름들. 채팅방 이름 입력시, 중복체크를 위해 필요.
    private List<String> serverRooms = new Vector<>();

    private JPanel jp_north = new JPanel();
    private JPanel jp_center = new JPanel();
    private JPanel jp_south = new JPanel();

    //GridLayout grid = null;
    private JLabel jlb_selectUser = new JLabel("접속중인 유저");
    private JCheckBox[] jcb_online = null;
    private JButton jbtn_create = new JButton("방 만들기");
    private JButton jbtn_invite = new JButton("추가 초대하기");

    public CreateChattingView() {
        initializeDisplay();
    }


    void checkBox(List<String> chatMember) {
        jp_center.setLayout(new GridLayout(chatMember.size(), 1, 2, 2));
        jcb_online = new JCheckBox[chatMember.size()];
        for (int i = 0; i < jcb_online.length; i++) {
            jcb_online[i] = new JCheckBox(chatMember.get(i));
//			jcb_online[i].addItemListener(ccHandler);
            jp_center.add(jcb_online[i]);
        }
    }


    //화면처리부
    private void initializeDisplay() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        //////상단
        jlb_selectUser.setFont(new Font("고딕체", Font.BOLD, 15));
        jp_north.add(jlb_selectUser);
        jp_north.setBackground(Color.WHITE);
        add("North", jp_north);

        ///////중단
        jp_center.setBackground(Color.WHITE);
        add("Center", jp_center);

        ///////하단
//		jbtn_create.addActionListener(ccHandler);
        jp_south.add(jbtn_create);
        add("South", jp_south);

        //////
        setTitle("초대 유저 선택");
        setBounds(1150, 200, 300, 400);
        setVisible(true);
    }

}
