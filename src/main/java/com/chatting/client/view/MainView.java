package com.chatting.client.view;


import com.chatting.client.model.Protocol;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class MainView extends JFrame {


    private JPanel panel_userList;
    private JPanel panel_chatList;

    private String online[] = {"아이디"};
    private String offline[] = {"fsad", "hefef"};

    private String room[] = {"방"};
    public MainView() {
        initializeDisplay();
    }

    private void initializeDisplay() {

        JTabbedPane tabbedPane = new JTabbedPane();

//        this.addWindowListener(defHandler);
        panel_userList = new UserList();
        panel_userList.add(new JLabel("유저목록", SwingConstants.CENTER));
        tabbedPane.addTab("유저목록", panel_userList);

        panel_chatList = new RoomList();
        panel_chatList.add(new JLabel("방목록", SwingConstants.CENTER));
        tabbedPane.addTab("방목록", panel_chatList);

        this.setTitle(Protocol.myID);
        this.setBounds(650, 200, 500, 600);
        this.setVisible(true);
        this.add(tabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void changeUserList() {

    }


    class UserList extends JPanel {

        private JLabel jlb_name = new JLabel(); // 사용자이름

        private JPanel panel_north = new JPanel();
        private JPanel panel_online = new JPanel();
        private JPanel panel_offline = new JPanel();
        private JPanel panel_south = new JPanel();




        public UserList() {
            jlb_name.setText(Protocol.myID + "님 환영합니다.");
            jlb_name.setFont(new Font("맑은고딕", Font.BOLD, 15));

            panel_north.setBounds(0, 20, 500, 40);
            panel_north.add(jlb_name);


            DefaultTableModel dtm_online = new DefaultTableModel(online, 0) {
                @Override //셀 더블클릭 후 수정 안되도록 조정.
                public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
                    return false;
                }
            };
            JTable jtb_online = new JTable(dtm_online);

            DefaultTableModel dtm_offline = new DefaultTableModel(offline, 0) {
                @Override //셀 더블클릭 후 수정 안되도록 조정.
                public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
                    return false;
                }
            };
            JTable jtb_offline = new JTable(dtm_offline);

           
            panel_online.setBounds(0, 60, 500, 200);
            panel_offline.setBounds(0, 280, 500, 200);


            // 중단
            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
            dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
            TableColumnModel tcm = jtb_online.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
            for (int i = 0; i < tcm.getColumnCount(); i++) {
                tcm.getColumn(i).setCellRenderer(dtcr);
            }
            tcm = jtb_offline.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
            for (int i = 0; i < tcm.getColumnCount(); i++) {
                tcm.getColumn(i).setCellRenderer(dtcr);
            }

            panel_online.add(new JLabel("온라인"));
            panel_online.add(new JScrollPane(jtb_online));

            panel_offline.add(new JLabel("오프라인"));
            panel_offline.add(new JScrollPane(jtb_offline));
            //jtb_online.addMouseListener(null);
            //jtb_offline.addMouseListener(null);


            JButton button_chat = new JButton("방 만들기");
            // 하단
//            button_chat.addActionListener(defHandler);
            panel_south.add(button_chat);

            JButton button_logout = new JButton("로그아웃");
//            jbtn_logout.addActionListener(defHandler);
            panel_south.add(button_logout);
            panel_south.setBounds(0, 500, 500, 40);

            JFrame.setDefaultLookAndFeelDecorated(true);
            setLayout(null);
            add(panel_north);
            add(panel_online); // 온라인 테이블 적용
            add(panel_offline); // 오프라인 테이블 적용
            add(panel_south);

        }
    }

    class RoomList extends JPanel {


        private JPanel jp_room = new JPanel();
        private JPanel panel_south2 = new JPanel();
        private DefaultTableModel dtm_room = new DefaultTableModel(room, 0) {
            @Override //셀 더블클릭 후 수정 안되도록 조정.
            public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
                return false;
            }
        };
        private JTable jtb_room = new JTable(dtm_room);
        private JScrollPane jsp_room = new JScrollPane(jtb_room);

        public RoomList() {

            jp_room.setBounds(0, 60, 500, 400);
            panel_south2.setBounds(0, 500, 500, 40);

            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
            dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
            TableColumnModel tcm = jtb_room.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
            for (int i = 0; i < tcm.getColumnCount(); i++) {
                tcm.getColumn(i).setCellRenderer(dtcr);
            }
            jp_room.add(new JLabel("방목록"));
            jp_room.add(jsp_room);

            JButton button_enter = new JButton("입장하기");
//            button_enter.addActionListener(defHandler);
            panel_south2.add(button_enter);

            JFrame.setDefaultLookAndFeelDecorated(true);
            setLayout(null);
            add(jp_room);
            add(panel_south2);

        }
    }
}
