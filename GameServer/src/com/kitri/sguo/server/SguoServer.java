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
			//�������� ����
			serverSocket = new ServerSocket(SguoConst.UPORT);
			System.out.println("���� ���...");
			//Ŭ���̾�Ʈ ���� ����
			userSocket = serverSocket.accept();
			System.out.println("Ŭ���̾�Ʈ ���� ����");
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
