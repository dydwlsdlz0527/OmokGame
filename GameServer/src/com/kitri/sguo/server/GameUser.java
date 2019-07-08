package com.kitri.sguo.server;

import java.net.Socket;

public class GameUser {
	
	private byte[] userimg;
	private String userid;
	private double shift; 
	private Socket socket;
	private GameRoom room;
	
	public GameUser() {}
	
	public GameUser(String userid) {
		this.userid = userid;
	}
	
	public GameUser(byte[] userimg, String userid, double shift) {
		this.userimg = userimg;
		this.userid = userid;
		this.shift = shift;
	}
	
	public void enterRoom(GameRoom room) {
		room.enterUser(this);
		this.room = room;
	}
	
	public void exitRoom(GameRoom room) {
		this.room = null;
	}

	public byte[] getUserimg() {
		return userimg;
	}

	public void setUserimg(byte[] userimg) {
		this.userimg = userimg;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public double getShift() {
		return shift;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setShift(double shift) {
		this.shift = shift;
	}

	public GameRoom getRoom() {
		return room;
	}

	public void setRoom(GameRoom room) {
		this.room = room;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null||getClass()!=obj.getClass()) return false;
		GameUser gameUser = (GameUser) obj;
		return userid == gameUser.userid;
	}
	
	@Override
	public int hashCode() {
		return userid.hashCode();
	}
}
