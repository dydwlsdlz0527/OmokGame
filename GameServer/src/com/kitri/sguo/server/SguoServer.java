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

		// ������ Ǯ.
		ExecutorService executorService;
		// ���� ����.
		ServerSocket serverSocket = null;
		// ������ ������ Ŭ���̾�Ʈ ���.
		List<ClientSample> connections = new Vector<>();
		Socket socket;
		DataOutputStream dos = null;
		DataInputStream dis = null;

		int PW = 123;

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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if (!serverSocket.isClosed()) {
					// stopServer();
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
							System.out.println(client + "@@@");
							// Ŭ���̾�Ʈ ��ü�� ����Ʈ�� ����.
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
			// ������ Ǯ���� ó��.
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
		
		//���� ��������� ��
		public void makeRoom(List<Object> list) {
			Runnable runnable = new Runnable() {
				public void run() {
					while(true) {
						
					}
				}
			};
			// ������ Ǯ���� ó��.
			executorService.submit(runnable);
		}
		
		public static void main(String[] args) {
			new SguoServer();
		}
		
		//�������� �ټ��� Ŭ���̾�Ʈ�� �����ϱ� ������ Ŭ���̾�Ʈ ���� ������ �����͸� �����ϱ� ���� �ν��Ͻ��� �����Ѵ�.
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
							System.out.println("������ �����Ͱ� ���۵�");
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

			// �������� ���� ������ �ޱ�
			void receive() {
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						try {
							InputStream inputStream = socket.getInputStream();
							byte[] Arr = new byte[100];
							// ������ �ޱ�, Ŭ���̾�Ʈ�� ������������ �������� ��� IOException �߻�
							int readByteCount = inputStream.read(Arr);
							if (readByteCount == -1) {
								throw new IOException();
							}
							System.out.println(
									"��û ó�� : " + socket.getRemoteSocketAddress() + ", " + Thread.currentThread().getName());
							String message = new String(Arr, 0, readByteCount, "UTF-8");
							// ��� Ŭ���̾�Ʈ���� ����
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
			//Ŭ���� ��.
		}

}
