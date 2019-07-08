package com.kitri.sguo.model.game;

import java.net.Socket;

import com.kitri.sguo.net.client.ClientSocket;
import com.kitri.sguo.view.game.GameView;

//실제로 게임하는 유저
public class GameUser {

	public int roomnum;
	public String userimg;
	public String userid;
	public String shift;
	//유저가 속한 게임
	public GameView room;
	Socket socket;
	
	//기본 생성자.
	public GameUser() {
		//socket = ClientSocket.getSocket();
	}
	
	//게임하는 유저의 이미지 || 아이디 || 승률
	public GameUser(int roomnum, String userimg, String userid, String shift) {
		this.roomnum = roomnum;
		this.userimg = userimg;
		this.userid = userid;
		this.shift = shift;
		socket = ClientSocket.getSocket();
	}
	//유저가 방에 입장한 후 유저가 속한 방을 룸으로 변경.
	public void enterRoom(GameView room) {
		socket = ClientSocket.getSocket();
		//room.enterUser(this);
		this.room = room;
	}
	
	//방에서 퇴장.
	public void exitRoom(GameView room) {
		this.room = null;
	}
	
	//equals와 hashCode를 사용해 동일 유저를 비교한다.
	//gameUser간 equals 비교, list에서 find 등
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
