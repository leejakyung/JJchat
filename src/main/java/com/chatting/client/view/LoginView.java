package com.chatting.client.view;

import com.chatting.client.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private LoginHandler loginHandler;
    private JLabel label_id = new JLabel("ID");
    private JLabel label_pw = new JLabel("PW");
    private JTextField field_id = new JTextField();
    private JPasswordField field_pw = new JPasswordField();
    private JButton button_login = new JButton("로그인");
    private JButton button_join = new JButton("회원가입");
    Font font = new Font("고딕체", Font.BOLD, 17);

    protected LoginView() {
        loginHandler = new LoginHandler(this);
        initializeDisplay();
    }

    private void initializeDisplay() {

        this.setLayout(null);
        this.add(label_id);
        this.add(label_pw);
        label_id.setFont(font);
        label_pw.setFont(font);
        label_id.setBounds(55, 200, 80, 40);
        label_pw.setBounds(55, 250, 80, 40);

        this.add(field_id);
		field_pw.addActionListener(loginHandler);
        this.add(field_pw);
        field_id.setBounds(120, 200, 185, 40);
        field_pw.setBounds(120, 250, 185, 40);

        button_login.addActionListener(new LoginHandler(this));
        this.add(button_login);
        button_login.setBounds(160, 300, 100, 40);
        button_join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e;
                if (o == button_login || o == field_id) {



                } else if (o == button_join) {


                }
            }
        });
        this.add(button_join);
        button_join.setBounds(160, 350, 100, 40);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(700, 200, 400, 600);
        this.setTitle("Login");
        this.setVisible(true);
    }

    public JLabel getLabel_id() {
        return label_id;
    }

    public JLabel getLabel_pw() {
        return label_pw;
    }

    public JTextField getField_id() {
        return field_id;
    }

    public JPasswordField getField_pw() {
        return field_pw;
    }

    public JButton getButton_login() {
        return button_login;
    }

    public JButton getButton_join() {
        return button_join;
    }
}
