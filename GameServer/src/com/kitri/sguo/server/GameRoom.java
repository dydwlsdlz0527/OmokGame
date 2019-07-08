package com.kitri.sguo.server;

import java.util.ArrayList;
import java.util.List;

public class GameRoom {

	private int id;
	private List<GameUser> userList;
	private GameUser roomOwner;
	private String roomName;
	
	//�� ó�� �游��� ��ư�� ���� ���� ����.
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
	//������ �濡 ������ �ش� ���ӹ� ����Ʈ�� �߰�.
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
			System.out.println("���ӹ� ��� ������� �����͸� ����.");
		}
	}
	//���ӹ� �̸� ����.
	public void setRoomName(String name) {
		this.roomName = name;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public int getUserSize() {
		return userList.size();
	}
	//������ ������.
	public GameUser getOwner() {
		return roomOwner;
	}
	//�� ��ȣ ����.
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
