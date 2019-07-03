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
					//게임에 접속한 유저의 소켓 생성.
					socket = new Socket();
					//서버에 연결 요청.
					socket.connect(new InetSocketAddress("localhost",SguoConst.SPORT));
					System.out.println("서버에 연결.");
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
	//서버에서 데이터 받기
	void receive() {
		while(true) {
			try {
				byte[] byteArr = new byte[100];
				InputStream inputStream = socket.getInputStream();
				int readByteCount = inputStream.read(byteArr);
				if(readByteCount==-1) {throw new IOException();}
				String data = new String(byteArr,0,readByteCount,"UTF-8");
				//받은 데이터의 protocol에 따라, 실행을 다르게 한다.
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
	//서버에 데이터 보내기
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
