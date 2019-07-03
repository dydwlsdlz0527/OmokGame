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
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.kitri.sguo.constdata.SguoConst;

public class SguoServer {

		// 스레드 풀.
		ExecutorService executorService;
		// 서버 소켓.
		ServerSocket serverSocket = null;
		// 서버에 접속한 클라이언트 담기.
		List<ClientSample> connections = new Vector<>();
		Socket socket;
		DataOutputStream dos = null;
		DataInputStream dis = null;

		int PW = 123;

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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if (!serverSocket.isClosed()) {
					// stopServer();
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
							System.out.println(client + "@@@");
							// 클라이언트 객체를 리스트에 담음.
							connections.add(client);
							System.out.println(connections.size());
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
			// 스레드 풀에서 처리.
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
		
		//방이 만들어졌을 때
		public void makeRoom(List<Object> list) {
			Runnable runnable = new Runnable() {
				public void run() {
					while(true) {
						
					}
				}
			};
			// 스레드 풀에서 처리.
			executorService.submit(runnable);
		}
		
		public static void main(String[] args) {
			new SguoServer();
		}
		
		//서버에서 다수의 클라이언트를 연결하기 때문에 클라이언트 별로 고유한 데이터를 저장하기 위해 인스턴스를 관리한다.
		class ClientSample {

			Socket socket;

			public ClientSample(Socket socket) {
				this.socket = socket;
				receive();
			}

			void send(String data) {
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						try {
							byte[] Arr = data.getBytes("UTF-8");
							OutputStream outputStream = socket.getOutputStream();
							System.out.println(socket);
							System.out.println(socket.getRemoteSocketAddress() + "@@@");
							outputStream.write(Arr);
							System.out.println("서버에 데이터가 전송됨");
							outputStream.flush();
						} catch (Exception e) {
							try {
								socket.close();
								e.printStackTrace();
							} catch (IOException e2) {
							}
						}
					}
				};
			}

			// 서버에서 보낸 데이터 받기
			void receive() {
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						try {
							InputStream inputStream = socket.getInputStream();
							byte[] Arr = new byte[100];
							// 데이터 받기, 클라이언트가 비정상적으로 종료했을 경우 IOException 발생
							int readByteCount = inputStream.read(Arr);
							if (readByteCount == -1) {
								throw new IOException();
							}
							System.out.println(
									"요청 처리 : " + socket.getRemoteSocketAddress() + ", " + Thread.currentThread().getName());
							String message = new String(Arr, 0, readByteCount, "UTF-8");
							// 모든 클라이언트에게 보냄
							for (ClientSample client : connections) {
								client.send(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				};
				executorService.submit(runnable);
			}
			//클래스 끝.
		}

}
