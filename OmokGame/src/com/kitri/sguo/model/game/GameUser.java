package com.kitri.sguo.model.game;

import java.net.Socket;

import com.kitri.sguo.net.client.ClientSocket;
import com.kitri.sguo.view.game.GameView;

//������ �����ϴ� ����
public class GameUser {

	public int roomnum;
	public String userimg;
	public String userid;
	public String shift;
	//������ ���� ����
	public GameView room;
	Socket socket;
	
	//�⺻ ������.
	public GameUser() {
		//socket = ClientSocket.getSocket();
	}
	
	//�����ϴ� ������ �̹��� || ���̵� || �·�
	public GameUser(int roomnum, String userimg, String userid, String shift) {
		this.roomnum = roomnum;
		this.userimg = userimg;
		this.userid = userid;
		this.shift = shift;
		socket = ClientSocket.getSocket();
	}
	//������ �濡 ������ �� ������ ���� ���� ������ ����.
	public void enterRoom(GameView room) {
		socket = ClientSocket.getSocket();
		//room.enterUser(this);
		this.room = room;
	}
	
	//�濡�� ����.
	public void exitRoom(GameView room) {
		this.room = null;
	}
	
	//equals�� hashCode�� ����� ���� ������ ���Ѵ�.
	//gameUser�� equals ��, list���� find ��
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null||getClass()!=obj.getClass()) return false;

		GameUser gameUser = (GameUser)obj;
			
		return userid ==gameUser.userid;
	}
	@Override
	public int hashCode() {
		return roomnum;
	}
}
