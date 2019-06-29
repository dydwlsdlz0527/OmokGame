package com.kitri.sguo.server;

import java.io.IOException;
import java.net.ServerSocket;

public class SguoServer {

	ServerSocket ss = null;
	
	public SguoServer() {
		try {
			ss = new ServerSocket();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
