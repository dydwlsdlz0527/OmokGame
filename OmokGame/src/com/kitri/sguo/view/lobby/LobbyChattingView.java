package com.kitri.sguo.view.lobby;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.kitri.sguo.net.constdata.SguoConst;

import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;

//채팅 화면
public class LobbyChattingView extends JPanel implements KeyListener {

	String memberid;
	// 자유롭게 사용하려면 여기에 필드로 선언해야 한다
	// 채팅창 프레임을 구성하는 컴포넌트
	// textarea 한줄 이상의 문자 입력 보여주기
	JTextArea textArea;
	JTextField sendMsgTf;
	JScrollPane scrollPane;
	BufferedWriter bw;
	MainLobbyView mainlobbyview;
	JPanel chatp;
	JPanel userlistp;
	JLabel listtitle;
	JTextArea userlist;
	JScrollPane jsp;

	public LobbyChattingView(String memberid, MainLobbyView mainlobbyview) {
//      try {
//         Socket socket = new Socket("localhost", 5000);
//         setSocket(socket);
//         TcpClientReceiveThread th1 = new TcpClientReceiveThread(socket);
//         new Thread(th1).start();
//      } catch (IOException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//      
		this.mainlobbyview = mainlobbyview;
		this.memberid = memberid;
		setLayout(null);

		chatp = new JPanel();
		chatp.setBounds(0, 0, 480, 445);
		add(chatp);
		chatp.setLayout(new BorderLayout(0, 0));
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textArea.setEditable(false);// 쓰기를 금지함

		scrollPane = new JScrollPane(textArea);
		chatp.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sendMsgTf = new JTextField();
		chatp.add(sendMsgTf, BorderLayout.SOUTH);
		sendMsgTf.setFont(new Font("굴림", Font.PLAIN, 16));
		sendMsgTf.addKeyListener(this);

		userlistp = new JPanel();
		userlistp.setBounds(477, 0, 153, 445);
		userlistp.setLayout(null);
		add(userlistp);

		listtitle = new JLabel("   \uC811\uC18D\uD55C \uC0AC\uB78C");
		listtitle.setFont(new Font("굴림", Font.BOLD, 14));
		listtitle.setBounds(0, 0, 153, 29);
		userlistp.add(listtitle);

		userlist = new JTextArea();
		userlist.setBackground(Color.WHITE);
		userlist.setForeground(Color.BLACK);
		userlist.setFont(new Font("Monospaced", Font.BOLD, 17));
		userlist.setBounds(1, 1, 207, 444);
		userlist.setEnabled(false);
		userlist.setEditable(false);
		// userlist.setBounds(0, 29, 120, 272);
		userlistp.add(userlist);

		jsp = new JScrollPane(userlist);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(0, 29, 153, 445);
		userlistp.add(jsp);

		setSize(630, 445);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("키눌림");
			String msg = sendMsgTf.getText();
			sendMsgTf.setText("");
			msg = SguoConst.MSGPORT + "||" + memberid + "||" + msg;
			mainlobbyview.send(msg);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}