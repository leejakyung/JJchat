package com.chatting.client.view;

import com.chatting.client.core.Client;
import com.chatting.client.model.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChatRoomView extends JFrame {

    private static final Logger logger = LogManager.getLogger(ChatRoomView.class);

    private String roomName = null;

    private final JPanel jp_file = new JPanel();
    private final StyledDocument sd_display = new DefaultStyledDocument(new StyleContext());
    
    public StyledDocument getSd_display() {
		return sd_display;
	}

	private String myId;
    private String targetId;
    private Client client;
    private String msg;

	public ChatRoomView() {
        initializeDisplay();
    }	

	public ChatRoomView(String myId, String targetId) {
		this.myId = myId;
		this.targetId = targetId;
		initializeDisplay();
	}

	public ChatRoomView(Client client, String myId, String targetId) {
		this.client = client;
		this.myId = myId;
		this.targetId = targetId;
		initializeDisplay();
	}
	
	public ChatRoomView(Client client, String myId, String targetId, String msg) {
		this.client = client;
		this.myId = myId;
		this.targetId = targetId;
		this.msg = msg;
		initializeDisplay();
	}

    private void initializeDisplay() {
        Font font = new Font("고딕체", Font.BOLD, 15);
        //채팅 메세지 보내기.

        JTextPane jtp_display = new JTextPane(sd_display);
        JScrollPane jsp_display = new JScrollPane(jtp_display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel jp_first = new JPanel();//채팅내용 보여주는 부분.
        jp_first.setLayout(new BorderLayout());

        //채팅창.
        //show 채팅내용
        jtp_display.setFont(font);
        jtp_display.setEditable(false);
        jsp_display.setViewportView(jtp_display);//작동 되는지 모르겠음
        jp_first.add("Center", jsp_display);

        JPanel jp_first_south = new JPanel();//채팅메세지 입력 부분.
        //채팅msg입력 및 전송 버튼 부분.
        jp_first_south.setLayout(new BorderLayout());

        JTextField jtf_msg = new JTextField();
        jtf_msg.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    logger.info(jtf_msg.getText());
                    jtf_msg.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        
        if (msg != null) {
        	try {
				sd_display.insertString(sd_display.getLength(), "<"+ targetId+">" + msg +"\n", null);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


        //메세지 보내기
        JButton jbtn_send = new JButton("전송");
        jbtn_send.addActionListener(e ->{
            logger.info("메세지 내용 : " + jtf_msg.getText());
            try {
            	msg = jtf_msg.getText();
                sd_display.insertString(sd_display.getLength(), "<"+ myId+">" + msg +"\n", null);
                jtf_msg.setText("");
                client.sendMessage(Protocol.sendMessage, myId, targetId, msg);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });


        jp_first_south.add("East", jbtn_send);
        jp_first_south.add("Center", jtf_msg);
        jp_first.add("South", jp_first_south);

        JPanel jp_second = new JPanel();
        jp_second.setLayout(new BorderLayout());


        //전송된 파일 보여주는 부분.
        jp_file.setLayout(new BoxLayout(jp_file, BoxLayout.Y_AXIS));

        //파일 panel title.(File Store)
        JLabel jlb_file_title = new JLabel("File Store");
        jlb_file_title.setForeground(Color.WHITE);
        jlb_file_title.setFont(font);

        JPanel jp_file_title = new JPanel();
        jp_file_title.add("Center", jlb_file_title);
        jp_file_title.setBackground(Color.DARK_GRAY);

        jp_second.add("North", jp_file_title);
        jp_second.add("Center", jp_file);
        //채팅방 나가기.
        JButton jbtn_exit = new JButton("나가기");
        jbtn_exit.addActionListener(e ->{

        });
        //초대하기
        JButton jbtn_invite = new JButton("초대하기");
        jbtn_invite.addActionListener(e ->{

        });
        //파일 전송
        JButton jbtn_file = new JButton("파일전송");
        jbtn_file.addActionListener(e ->{
            FileDialog fd = new FileDialog(this, "파일탐색기", FileDialog.LOAD);
            fd.setSize(400, 300);
            fd.setVisible(true);
        });

        JPanel jp_second_south = new JPanel();
        //파일전송, 나가기 버튼 부분.
        jp_second.add("South", jp_second_south);
        jp_second_south.setLayout(new GridLayout(0, 1, 2, 2));
        jp_second_south.add(jbtn_file);
        jp_second_south.add(jbtn_invite);
        jp_second_south.add(jbtn_exit);

        JScrollPane jsp_file_display = new JScrollPane(jp_file,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
                , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add("Center", jp_first);
        this.add("East", jp_second);
        this.setBounds(650, 200, 500, 600);
        this.setVisible(true);
    }

    public void addFileName(String fileName){
        JLabel jlb_file = new JLabel(fileName);
        jlb_file.setForeground(Color.BLUE.darker());
        jlb_file.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jlb_file.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info(fileName);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        jp_file.add(jlb_file);
        jp_file.revalidate();
    }

}
