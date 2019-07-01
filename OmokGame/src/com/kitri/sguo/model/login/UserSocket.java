package com.kitri.sguo.model.login;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.kitri.sguo.constdata.SguoConst;

public class UserSocket {
	
	private Socket usersocket;
	private HashMap<String,MemberDAO> userinfomap;
	
	public UserSocket() {
		try {
			usersocket = new Socket("localhost",SguoConst.UPORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void FindUserInfo() {
		
	}

}
