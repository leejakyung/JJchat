package com.chatting.client.view;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class ChatRoomView extends JFrame {


    private String roomName = null;
    private FileDialog fd = new FileDialog(this);

    private JPanel jp_first = new JPanel();//채팅내용 보여주는 부분.
    private JPanel jp_first_south = new JPanel();//채팅메세지 입력 부분.
    private JPanel jp_second = new JPanel();
    private JPanel jp_second_south = new JPanel();

    private JPanel jp_file = new JPanel();
    private JPanel jp_file_title = new JPanel();
    private JLabel jlb_file_title = new JLabel("File Store");

    private StyledDocument sd_display =
            new DefaultStyledDocument(
                    new StyleContext());
    private JTextField jtf_msg = new JTextField();
    private JTextPane jtp_display = new JTextPane(sd_display);

    private JScrollPane jsp_display = new JScrollPane(jtp_display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
            , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JScrollPane jsp_file_display = new JScrollPane(jp_file,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
            , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JButton jbtn_send = new JButton("전송");
    private JButton jbtn_exit = new JButton("나가기");
    private JButton jbtn_invite = new JButton("초대하기");
    private JButton jbtn_file = new JButton("파일전송");
    private Font font = new Font("고딕체", Font.BOLD, 15);

    public ChatRoomView() {
        initializeDisplay();
    }

    private void initializeDisplay() {

        //채팅 메세지 보내기.
//		jtf_msg.addActionListener(crHandler);
//		jbtn_send.addActionListener(crHandler);

        //채팅방 나가기.
//		jbtn_exit.addActionListener(crHandler);

        //초대하기
//		jbtn_invite.addActionListener(crHandler);

        //파일 전송
//		jbtn_file.addActionListener(crHandler);

        //show 채팅내용
        jtp_display.setFont(font);
        jtp_display.setEditable(false);
        jsp_display.setViewportView(jtp_display);//작동 되는지 모르겠음

        //채팅창.
        jp_first.setLayout(new BorderLayout());
        jp_first.add("Center", jsp_display);
        jp_first.add("South", jp_first_south);

        //채팅msg입력 및 전송 버튼 부분.
        jp_first_south.setLayout(new BorderLayout());
        jp_first_south.add("Center", jtf_msg);
        jp_first_south.add("East", jbtn_send);

        //전송된 파일 보여주는 부분.
        jp_second.setLayout(new BorderLayout());
        jp_second.add("Center", jp_file);
        jp_second.add("North", jp_file_title);
        jp_file.setLayout(new BoxLayout(jp_file, BoxLayout.Y_AXIS));

        //파일 panel title.(File Store)
        jp_file_title.add("Center", jlb_file_title);
        jp_file_title.setBackground(Color.DARK_GRAY);
        jlb_file_title.setForeground(Color.WHITE);
        jlb_file_title.setFont(font);

        //파일전송, 나가기 버튼 부분.
        jp_second.add("South", jp_second_south);
        jp_second_south.setLayout(new GridLayout(0, 1, 2, 2));
        jp_second_south.add(jbtn_file);
        jp_second_south.add(jbtn_invite);
        jp_second_south.add(jbtn_exit);


        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add("Center", jp_first);
        this.add("East", jp_second);
        this.setBounds(650, 200, 500, 600);
        this.setVisible(true);
    }

}
