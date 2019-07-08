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

	// ������ Ǯ.
	ExecutorService executorService;
	// ���� ����.
	ServerSocket serverSocket = null;
	// ������ ������ Ŭ���̾�Ʈ
	List<ClientSample> connections = new Vector<>();
	// ������ ���ӹ�
	List<GameRoom> roomlist;
	// ���� �� ���� ����Ʈ
	List<GameUser> userList;
	// ������ ���� ����Ʈ
	List<String> ulist = new Vector<>();
	Socket socket;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	//������ ����� �� ���ӹ� �Ŵ��� ����.
	RoomManager roommanager;
	//����
	GameUser user;


	public SguoServer() {
		JFrame f = new JFrame();
		JButton bt = new JButton("��������");

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
		System.out.println("���� ����.");
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", SguoConst.SPORT)); // ip�� �н����带 ���ε��Ѵ�.
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
		// �����带 ���ؼ� ������ ���� ��û�� ������.
		Runnable runnable = new Runnable() {
			public void run() {
				while (true) {
					try {
						socket = serverSocket.accept();
						String message = "[���� ���� : " + socket.getRemoteSocketAddress() + " : "
								+ Thread.currentThread().getName() + "]";
						System.out.println(message);
						// Ŭ���̾�Ʈ�� ��û�ϸ� ������ ������ ������ �����
						// Ŭ���̾�Ʈ ������ ������.
						// ��? ������ ������Ǯ�� ����� ������� ��û�� ��� �޾ƾ��ϱ� ������.
						ClientSample client = new ClientSample(socket);
						System.out.println("������ Ŭ���̾�Ʈ ���� : " + client);
						// Ŭ���̾�Ʈ ��ü�� ����Ʈ�� ����.
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

	// �������� �ټ��� Ŭ���̾�Ʈ�� �����ϱ� ������ Ŭ���̾�Ʈ ���� ������ �����͸� �����ϱ� ���� �ν��Ͻ��� �����Ѵ�.
	class ClientSample {
		Socket socket;

		public ClientSample(Socket socket) {
			this.socket = socket;
			receive();
		}

		// ������ �޼��� ������.
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

		// Ŭ���̾�Ʈ�� ������ ���� �޼��� �ޱ�.
		synchronized void receive() {
			System.out.println("�����ʸ��ú�");
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							InputStream inputStream = socket.getInputStream();
							byte[] Arr = new byte[1000];
							// ������ �ޱ�, Ŭ���̾�Ʈ�� ������������ �������� ��� IOException �߻�
							int readByteCount = inputStream.read(Arr);
							if (readByteCount == -1) {
								throw new IOException();
							}
//						System.out.println(
//								"��û ó�� : " + socket.getRemoteSocketAddress() + ", " + Thread.currentThread().getName());
							String message = new String(Arr, 0, readByteCount, "UTF-8");
							StringTokenizer st = new StringTokenizer(message, "||");
							int protocol = Integer.parseInt(st.nextToken());
							System.out.println("���� �� �������� : " + protocol);
							switch (protocol) {
							// �� ����� ��Ʈ || ���� || ���̵� || ���� ��� || ���� ���
							case SguoConst.MRPROT: {
								String roomtitle = st.nextToken();
								String ownerid = st.nextToken();
								String userrankname = st.nextToken();
								String limitrank = st.nextToken();
								message = SguoConst.MRPROT + "||" + roomtitle + "||" + ownerid + "||" + userrankname + "||" + limitrank; 
								break;
							}
							// ���� ��� ���̵� || �޼���
							case SguoConst.MSGPORT: {
								String userid = st.nextToken();
								String txtmsg = st.nextToken();
								message = SguoConst.MSGPORT + "||" + userid + "||" + txtmsg;
								System.out.println("������ ä�� �޼��� : " + message);
								break;
							}
							// ������ ��� ����
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
								System.out.println("����Ʈ ���̵� : " + userid);
								message = msg2 + userid;
								break;
							}
							// �� ��ȣ || �̹��� || ���̵� || �·�
							case SguoConst.GOGAME: {
								String uimage = st.nextToken();
								String userid = st.nextToken();
								String shift = st.nextToken();
								user = new GameUser(uimage,userid,shift);
								userList.add(user);
								System.out.println("����Ʈ ũ�� = " + userList.size());
								GameRoom room = RoomManager.createRoom(user);
								message = SguoConst.GOGAME + "||" + room.getId() +"||"+ uimage + "||" + userid + "||"
										+ shift;
								break;
							}
							// �κ� ������
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
							// ��� Ŭ���̾�Ʈ���� ����
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

	}// Ŭ���� ��

}
