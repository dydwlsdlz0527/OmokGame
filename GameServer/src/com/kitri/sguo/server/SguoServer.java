package com.kitri.sguo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.kitri.sguo.constdata.SguoConst;

public class SguoServer implements Runnable{

	ServerSocket serverSocket = null;
	Socket userSocket = null;
	
	
	public SguoServer() {
		
		try {
			//서버소켓 생성
			serverSocket = new ServerSocket(SguoConst.UPORT);
			System.out.println("접속 대기...");
			//클라이언트 연결 수락
			userSocket = serverSocket.accept();
			System.out.println("클라이언트 연결 수락");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
	}
	
	public static void main(String[] args) {
		new SguoServer();
	}
}
