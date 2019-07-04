package com.kitri.sguo.net.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import com.kitri.sguo.net.constdata.SguoConst;

//ΩÃ±€≈Ê∆–≈œ
public class ClientSocket {

	private static Socket socket = null;
	
	private ClientSocket() {}

	public static synchronized Socket getSocket() {

		if (socket == null) {
			socket = new Socket();
		}
		if (!socket.isConnected()) {
			try {
				socket.connect(new InetSocketAddress(SguoConst.HOST, SguoConst.SPORT));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return socket;
	}


}
