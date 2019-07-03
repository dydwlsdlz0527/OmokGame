package com.kitri.sguo.net.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import com.kitri.sguo.net.constdata.SguoConst;


public class UserSoc {
	
	Socket socket;
	int count = 0;
	String userid;
	
	public UserSoc(String userid) {
		startClient();
		this.userid = userid;
	}
	
	void startClient() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					//���ӿ� ������ ������ ���� ����.
					socket = new Socket();
					//������ ���� ��û.
					socket.connect(new InetSocketAddress("localhost",SguoConst.SPORT));
					System.out.println("������ ����.");
				}catch(Exception e) {
					if(!socket.isClosed()) {stopClient();}
					e.printStackTrace();
				}
				receive();
			}
		};
		thread.start();
	}
	
	void stopClient() {
		if(socket!=null&&!socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//�������� ������ �ޱ�
	void receive() {
		while(true) {
			try {
				byte[] byteArr = new byte[100];
				InputStream inputStream = socket.getInputStream();
				int readByteCount = inputStream.read(byteArr);
				if(readByteCount==-1) {throw new IOException();}
				String data = new String(byteArr,0,readByteCount,"UTF-8");
				//���� �������� protocol�� ����, ������ �ٸ��� �Ѵ�.
				StringTokenizer st = new StringTokenizer(data);
				int protocol = Integer.parseInt(st.nextToken());
				switch(protocol) {
				 
				}
				System.out.println(data);
			} catch (IOException e) {
				e.printStackTrace();
				stopClient();
				break;
			}
		}
	}
	//������ ������ ������
	void send(String data) {
		Thread thread = new Thread() {
			public void run() {
				try {
					byte[] byteArr = data.getBytes("UTF-8");
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(byteArr);
					outputStream.flush();
				}catch(Exception e) {
					stopClient();
				}
			}
		};
		thread.start();
	}
	
	void makeroom() {
		
	}
}
