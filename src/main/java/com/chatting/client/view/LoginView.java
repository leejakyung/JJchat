package com.chatting.client.view;

import com.chatting.client.core.Client;
import com.chatting.client.model.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class LoginView extends JFrame {

    private static final Logger logger = LogManager.getLogger(LoginView.class);
    private final Client client;

    public JTextField field_id;
    public JPasswordField field_pw;


    public LoginView(Client client) {
        this.client = client;
        initializeDisplay();
        initialize();
    }

    private void initialize() {
        logger.info("로그인 뷰 생성 성공");
        logger.warn("로그인 뷰 생성 성공");
        logger.error("로그인 뷰 생성 성공");
    }

    private void initializeDisplay() {

        Font font = new Font("고딕체", Font.BOLD, 17);
        JLabel label_id = new JLabel("ID");
        JLabel label_pw = new JLabel("PW");

        field_id = new JTextField();
        field_pw = new JPasswordField();

        this.setLayout(null);
        this.add(label_id);
        this.add(label_pw);
        label_id.setFont(font);
        label_pw.setFont(font);
        label_id.setBounds(55, 200, 80, 40);
        label_pw.setBounds(55, 250, 80, 40);

        this.add(field_id);

        field_pw.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String msg = Protocol.checkLogin+Protocol.seperator+field_id.getText()+Protocol.seperator+field_pw.getText();
                    logger.info("서버로 메세지 전송 : {}", msg);
                    try {
                        client.getOos().writeObject(msg);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.add(field_pw);
        field_id.setBounds(120, 200, 185, 40);
        field_pw.setBounds(120, 250, 185, 40);

        JButton button_login = new JButton("로그인");
        button_login.addActionListener(e -> {
            client.sendMessage(Protocol.checkLogin, field_id.getText(), field_pw.getText());
        });
        
        this.add(button_login);
        button_login.setBounds(160, 300, 100, 40);

        JButton button_join = new JButton("회원가입");
        button_join.addActionListener(e -> {

        });
        this.add(button_join);
        button_join.setBounds(160, 350, 100, 40);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(700, 200, 400, 600);
        this.setTitle("Login");
        this.setVisible(true);
    }
}
