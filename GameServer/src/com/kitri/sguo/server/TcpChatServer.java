package com.kitri.sguo.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class TcpChatServerManager {
	private List<Socket> socketList;
	
	//������: TCSM ��ü�� �����ϸ� ���ϸ���Ʈ �ϳ� ����
	public TcpChatServerManager() {
		socketList = new ArrayList<Socket>();
	}
	
	//���� �߰� �޼ҵ�
	public void addSocket(Socket socket) {
		this.socketList.add(socket);
		new Thread(new ReceiverThread(socket)).start();
	}
	
	//��ƼŬ���̾�Ʈ�� ������ ���ÿ� �����ϱ� ���� ������ ����
	//������ ���������� ������ �־����.
	class ReceiverThread implements Runnable {
		private Socket socket;//����
		
		public ReceiverThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			
			try {
				//Ŭ���̾�Ʈ�� ���� �޽��� �б� ���� �۾�
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while (true) {
					String msg = br.readLine();//Ŭ���̾�Ʈ�� ���� �޽��� �б�
					String memberid = br.readLine();
					System.out.println(msg);
					//���� �޽����� ���� Ŭ���̾�Ʈ �����ϰ�
					//��� Ŭ���̾�Ʈ���� ������
					Socket tmpSocket = null;
					try {
						for (int i = 0; i < socketList.size(); i++) {
							tmpSocket = socketList.get(i);
							//socketList.get(0) -> ���� ��ü
							
							if (socket.equals(tmpSocket)) {
								continue;
								//�޽����� ���� Ŭ���̾�Ʈ��� �ݺ��� �ѹ� �ǳʶٱ�
							}
							
							//������ ���� �޽����� Ŭ���̾�Ʈ�� �۽��ϱ�
							BufferedWriter bw = new BufferedWriter(
									new OutputStreamWriter(tmpSocket.getOutputStream()));
							
							bw.write(msg + "\n");
							bw.write(memberid + "\n");
							bw.flush();								
						}
					} catch (Exception e) {
						System.out.println(tmpSocket.getRemoteSocketAddress() + "���� ����");
						socketList.remove(tmpSocket);
						//������ ���� Ŭ���̾�Ʈ�� ���� ���� ����
						
						System.out.println("=====����������=====");
						for (Socket s : socketList) {
							System.out.println(s.getRemoteSocketAddress());
						}
						System.out.println("=================");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if(socket != null) {
					System.out.println(socket.getRemoteSocketAddress() + "���� ����");
					socketList.remove(socket);
					//������ ���� Ŭ���̾�Ʈ�� ���� ���� ����
					
					System.out.println("=====����������=====");
					for (Socket s : socketList) {
						System.out.println(s.getRemoteSocketAddress());
					}
					System.out.println("=================");
				}
			}
			
		}
		
	}
	
}


public class TcpChatServer {	

	public static void main(String[] args) {
		//��������
		TcpChatServerManager tcsm = new TcpChatServerManager();
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			while (true) {
				Socket socket = serverSocket.accept();//Ŭ���̾�Ʈ �����û ���(�����û �������� ����)
				//-> �����û����? ������ ��ȯ
				//��Ƽ Ŭ���̾�Ʈ -> ������ ������ -> ����Ʈ�� ����
				//-> ���� �Ŵ��� Ŭ������ ����
				System.out.println(socket.getRemoteSocketAddress() + " : ����");
				tcsm.addSocket(socket);//���� ���� �����Ŵ����� ���ϸ�Ͽ� �߰�
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}