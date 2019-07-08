package com.kitri.sguo.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.kitri.sguo.constdata.SguoConst;

public class SguoServer {

	// 스레드 풀.
	ExecutorService executorService;
	// 서버 소켓.
	ServerSocket serverSocket = null;
	// 서버에 접속한 클라이언트
	List<ClientSample> connections = new Vector<>();
	// 개설된 게임방
	List<GameRoom> roomlist;
	// 게임 속 유저 리스트
	List<GameUser> userList;
	// 접속한 유저 리스트
	List<String> ulist = new Vector<>();
	Socket socket;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	//서버가 실행될 때 게임방 매니저 생성.
	RoomManager roommanager;
	//유저
	GameUser user;


	public SguoServer() {
		JFrame f = new JFrame();
		JButton bt = new JButton("서버열기");

		f.setSize(300, 300);
		f.add(bt, BorderLayout.SOUTH);
		f.setVisible(true);
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverStart();
			}
		});

	}

	public void serverStart() {
		System.out.println("서버 실행.");
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", SguoConst.SPORT)); // ip와 패스워드를 바인딩한다.
			roommanager = new RoomManager();
			userList = new Vector<>();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}
		// 스레드를 통해서 소켓의 연결 요청을 수락함.
		Runnable runnable = new Runnable() {
			public void run() {
				while (true) {
					try {
						socket = serverSocket.accept();
						String message = "[연결 수락 : " + socket.getRemoteSocketAddress() + " : "
								+ Thread.currentThread().getName() + "]";
						System.out.println(message);
						// 클라이언트가 요청하면 서버가 생성한 소켓을 사용해
						// 클라이언트 소켓을 구분함.
						// 왜? 서버는 스레드풀을 사용해 사용자의 요청을 계속 받아야하기 때문에.
						ClientSample client = new ClientSample(socket);
						System.out.println("서버의 클라이언트 소켓 : " + client);
						// 클라이언트 객체를 리스트에 담음.
						connections.add(client);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						if (!serverSocket.isClosed()) {
							stopServer();
						}
						break;
					}
				}
			}
		};
		executorService.submit(runnable);
	}

	public void stopServer() {
		try {
			Iterator<ClientSample> iterator = connections.iterator();
			while (iterator.hasNext()) {
				ClientSample client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			if (executorService != null && !executorService.isShutdown()) {
				executorService.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SguoServer();
	}

	// 서버에서 다수의 클라이언트를 연결하기 때문에 클라이언트 별로 고유한 데이터를 저장하기 위해 인스턴스를 관리한다.
	class ClientSample {
		Socket socket;

		public ClientSample(Socket socket) {
			this.socket = socket;
			receive();
		}

		// 서버가 메세지 보내기.
		void send(String data) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						byte[] Arr = data.getBytes("UTF-8");
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(Arr);
						outputStream.flush();
					} catch (Exception e) {
						try {
							socket.close();
						} catch (IOException e2) {
						}
					}
				}
			};
			executorService.submit(runnable);
		}

		// 클라이언트가 서버에 보낸 메세지 받기.
		synchronized void receive() {
			System.out.println("서버쪽리시브");
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							InputStream inputStream = socket.getInputStream();
							byte[] Arr = new byte[1000];
							// 데이터 받기, 클라이언트가 비정상적으로 종료했을 경우 IOException 발생
							int readByteCount = inputStream.read(Arr);
							if (readByteCount == -1) {
								throw new IOException();
							}
//						System.out.println(
//								"요청 처리 : " + socket.getRemoteSocketAddress() + ", " + Thread.currentThread().getName());
							String message = new String(Arr, 0, readByteCount, "UTF-8");
							StringTokenizer st = new StringTokenizer(message, "||");
							int protocol = Integer.parseInt(st.nextToken());
							System.out.println("서버 쪽 포로토콜 : " + protocol);
							switch (protocol) {
							// 방 만들기 포트 || 방제 || 아이디 || 방장 등급 || 제한 등급
							case SguoConst.MRPROT: {
								String roomtitle = st.nextToken();
								String ownerid = st.nextToken();
								String userrankname = st.nextToken();
								String limitrank = st.nextToken();
								message = SguoConst.MRPROT + "||" + roomtitle + "||" + ownerid + "||" + userrankname + "||" + limitrank; 
								break;
							}
							// 보낸 사람 아이디 || 메세지
							case SguoConst.MSGPORT: {
								String userid = st.nextToken();
								String txtmsg = st.nextToken();
								message = SguoConst.MSGPORT + "||" + userid + "||" + txtmsg;
								System.out.println("서버쪽 채팅 메세지 : " + message);
								break;
							}
							// 접속한 모든 유저
							case SguoConst.ULIST: {
								String msg2 = SguoConst.ULIST + "||";
								for (int i = 0; i < ulist.size(); i++) {
									if (i != ulist.size()) {
										msg2 += ulist.get(i) + "||";
									}
								}
								System.out.println(msg2);
								String userid = st.nextToken();
								ulist.add(userid);
								System.out.println("리스트 아이디 : " + userid);
								message = msg2 + userid;
								break;
							}
							// 방 번호 || 이미지 || 아이디 || 승률
							case SguoConst.GOGAME: {
								String uimage = st.nextToken();
								String userid = st.nextToken();
								String shift = st.nextToken();
								user = new GameUser(uimage,userid,shift);
								userList.add(user);
								System.out.println("리스트 크기 = " + userList.size());
								GameRoom room = RoomManager.createRoom(user);
								message = SguoConst.GOGAME + "||" + room.getId() +"||"+ uimage + "||" + userid + "||"
										+ shift;
								break;
							}
							// 로비 나가기
							case SguoConst.EXITLOBBY: {
								String userid = st.nextToken();
								int index = ulist.indexOf(userid);
								ulist.remove(index);
								String msg2 = SguoConst.EXITLOBBY + "||";
								for (int i = 0; i < ulist.size(); i++) {
									if (i == ulist.size() - 1) {
										msg2 += ulist.get(i);
									} else {
										msg2 += ulist.get(i) + "||";
									}
								}
								System.out.println(message + "!!!!");
								message = msg2;
								break;
							}
							case SguoConst.RLIST : {
								String msg = "";
								for(int i=0;i<roomlist.size();i++) {
									if(i==0) {
										
									}
								}
								break;
							}
							}
							System.out.println(message + "@@");
							// 모든 클라이언트에게 보냄
							for (ClientSample client : connections) {
								client.send(message);
							}
						} catch (IOException e) {
						}
					}
				}
			};

			executorService.submit(runnable);
		}

	}// 클래스 끝

}
