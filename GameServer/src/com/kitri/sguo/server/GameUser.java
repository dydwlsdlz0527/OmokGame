package com.kitri.sguo.server;

import java.net.Socket;

public class GameUser {
	
	String userimg;
	String userid;
	String shift; 
	GameRoom room;
	
	public GameUser() {}
	
	public GameUser(String userid) {
		this.userid = userid;
	}
	
	public GameUser(String userimg, String userid, String shift) {
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

	public String getUserimg() {
		return userimg;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
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
