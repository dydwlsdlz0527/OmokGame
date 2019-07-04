package com.kitri.sguo.view.lobby;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.kitri.sguo.controller.lobby.LobbyController;
import com.kitri.sguo.model.login.MemberDAO;
import com.kitri.sguo.net.client.ClientSocket;
import com.kitri.sguo.net.constdata.SguoConst;

//로그인 후 들어가는 방
//게임 대기 방
public class MainLobbyView extends JFrame {

	// 스레드 풀.
	ExecutorService executorService;
	MemberDAO mdao = new MemberDAO();
	MainBtnsP lobbyBtns;
	JPanel rankview;
	UserInfoP userInfo;
	JPanel gameRooms;
	JScrollPane png;
	Socket socket;
	LobbyChattingView chattingview;
	String memberid;

	public MainLobbyView(String memberid) {
		this.memberid = memberid;
		getContentPane().setBackground(Color.ORANGE);
		setBackground(Color.ORANGE);
		setResizable(false);
		getContentPane().setForeground(Color.ORANGE);

		setVisible(true);
		setSize(1000, 1000 / 4 * 3);
		getContentPane().setEnabled(false);
		getContentPane().setLayout(null);

		userInfo = new UserInfoP(mdao.getUserInfo(memberid));
		userInfo.setBackground(Color.ORANGE);
		userInfo.setBounds(0, 0, 360, 277);
		getContentPane().add(userInfo);

		gameRooms = new JPanel();
		gameRooms.setLayout(new GridLayout(50, 1));
		gameRooms.add(new RoomsP());
		png = new JScrollPane(gameRooms);
		png.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		png.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		png.setBounds(362, 0, 630, 278);
		gameRooms.setBounds(362, 0, 630, 278);
		gameRooms.setBackground(Color.ORANGE);
		gameRooms.setForeground(Color.BLACK);
		gameRooms.setSize(630, 278);

		getContentPane().add(png);

		rankview = new JPanel();
		rankview.setBackground(Color.ORANGE);
		rankview.setBounds(0, 462, 363, 260);
		getContentPane().add(rankview);

		lobbyBtns = new MainBtnsP();
		lobbyBtns.setForeground(Color.ORANGE);
		lobbyBtns.setBounds(0, 275, 363, 187);
		getContentPane().add(lobbyBtns);

		chattingview = new LobbyChattingView(memberid, this);
		chattingview.setBackground(Color.ORANGE);
		chattingview.setBounds(363, 277, 630, 445);
		getContentPane().add(chattingview);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		// 접속한 유저의 소켓 생성.
		startClient();
	}

	public void MakeLobbyController() {
		LobbyController lobbycontroller = new LobbyController(this, this.userInfo, userInfo.getUserId.getText(),
				gameRooms);
		lobbyBtns.exitbtn.addActionListener(lobbycontroller);
		lobbyBtns.sendmessagebtn.addActionListener(lobbycontroller);
		lobbyBtns.gameinfo.addActionListener(lobbycontroller);
		lobbyBtns.gamewithcombtn.addActionListener(lobbycontroller);
		lobbyBtns.makeroombtn.addActionListener(lobbycontroller);
		userInfo.userModified.addActionListener(lobbycontroller);
		userInfo.secession.addActionListener(lobbycontroller);
	}

	void startClient() {
			try {
				// 싱글톤 패턴
				// 게임에 접속한 유저의 소켓 생성.
				socket = ClientSocket.getSocket();
				// 서버에 연결 요청.
				// socket.connect(new InetSocketAddress("localhost", SguoConst.SPORT));
				System.out.println("서버에 연결.");
			} catch (Exception e) {
				if (!socket.isClosed()) {
					stopClient();
				}
				e.printStackTrace();
			}
			Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while(true) {
				send(SguoConst.ULIST + "||" + memberid);
				receive();
				}
			}
		};
		executorService.submit(runnable);
		
//		Thread thread = new Thread() {
//			@Override
//			public void run() {
//				
//			}
//		};
//		thread.start();
	}

	void stopClient() {
		if (socket != null && !socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 서버에 데이터 보내기
	void send(String data) {
		Thread thread = new Thread() {
			public void run() {
				try {
					System.out.println("서버에 보내는 데이터 : " + data);
					byte[] byteArr = data.getBytes("UTF-8");
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(byteArr);
					outputStream.flush();
				} catch (Exception e) {
					stopClient();
				}
			}
		};
		thread.start();
	}

	// 서버에서 데이터 받기
	void receive() {
		System.out.println("리시브");
		while (true) {
			try {
				byte[] byteArr = new byte[100];
				InputStream inputStream = socket.getInputStream();
				int readByteCount = inputStream.read(byteArr);
				if (readByteCount == -1) {
					throw new IOException();
				}
				String data = new String(byteArr, 0, readByteCount, "UTF-8");
				// 받은 데이터의 protocol에 따라, 실행을 다르게 한다.
				StringTokenizer st = new StringTokenizer(data, "||");
				int protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				case SguoConst.MSGPORT: {
					String userid = st.nextToken();
					String txtmsg = st.nextToken();
					System.out.println(userid);
					System.out.println(txtmsg);
					chattingview.textArea.append("[" + userid + "]" + " : " + txtmsg + "\n");
					break;
				}
				case SguoConst.ULIST: {
					String userid = st.nextToken();
					chattingview.userlist.append(userid + "\n");
					break;
				}
				}
			} catch (IOException e) {
				e.printStackTrace();
				stopClient();
				break;
			}
		}
	}

}
