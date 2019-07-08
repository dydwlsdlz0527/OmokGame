package com.kitri.sguo.server;

import java.util.ArrayList;
import java.util.List;

public class GameRoom {

	private int id;
	private List<GameUser> userList;
	private GameUser roomOwner;
	private String roomName;
	
	//맨 처음 방만들기 버튼을 통해 방을 생성.
	public GameRoom(int roomid) {
		this.id = roomid;
		userList = new ArrayList<>();
	}
	
	public GameRoom(GameUser user) {
		userList = new ArrayList<>();
		user.enterRoom(this);
		userList.add(user);
		this.roomOwner = user;
	}
	//유저가 방에 들어오면 해당 게임방 리스트에 추가.
	public void enterUser(GameUser user) {
		user.enterRoom(this);
		userList.add(user);
	}
	
	public void close() {
		for(GameUser user : userList) {
			user.exitRoom(this);
		}
		this.userList.clear();
		this.userList = null;
	}
	
	public void broadcast(byte[] data) {
		for(GameUser user : userList) {
			System.out.println("게임방 모든 사람에게 데이터를 보냄.");
		}
	}
	//게임방 이름 설정.
	public void setRoomName(String name) {
		this.roomName = name;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public int getUserSize() {
		return userList.size();
	}
	//방장을 리턴함.
	public GameUser getOwner() {
		return roomOwner;
	}
	//방 번호 리턴.
	public int getId() {
		return id;
	}
	public List getUserList() {
		return userList;
	}
	public GameUser getRoomOwner() {
		return roomOwner;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null||getClass()!=obj.getClass()) return false;
		GameRoom gameRoom = (GameRoom) obj;
		return id == gameRoom.id;
	}
	
	@Override
	public int hashCode() {
		return id;
	}

	public void setOwner(GameUser owner) {
		this.roomOwner = owner;
	}
}
