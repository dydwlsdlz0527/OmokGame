package com.kitri.sguo.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.kitri.sguo.constdata.SguoConst;

public class SguoServer implements Runnable{

	ServerSocket ss = null;
	
	
	
	public SguoServer() {
		try {
			ss = new ServerSocket(SguoConst.UPORT);
			System.out.println("접속 대기...");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		
	}
}
