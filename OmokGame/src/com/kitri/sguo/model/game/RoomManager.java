package com.kitri.sguo.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.kitri.sguo.view.game.GameView;

public class RoomManager {

	//방 리스트
	static List<GameView> roomList;
	//static AtomicInteger atomicInteger;
	
	static {
		roomList = new ArrayList<>();
		//atomicInteger = new AtomicInteger();
	}
	
	public RoomManager() {}
	
	
	public static GameView createRoom(GameUser owner) {
		owner.roomnum = owner.roomnum;
		GameView room = new GameView(owner);
		room.enterUser(owner);
		room.setOwner(owner);
		roomList.add(room);
		System.out.println("리스트 사이즈 : " + roomList.size());
		return room;
	}
	
	public static GameView getRoom(GameView gameRoom) {
		int idx = roomList.indexOf(gameRoom);
		if(idx>0) {
			return roomList.get(idx);
		}else {
			return null;
		}
	}
	
	public static void removeRoom(GameView room) {
		room.close();
		roomList.remove(room);
		System.out.println("방 제거!");
	}
	
	public static int roomCount() {
		return roomList.size();
	}
	
	public static GameView getRoomN(int roomnum) {
		System.out.println("가져올 방의 번호 : " + roomnum);
		return roomList.get(roomnum);
	}
}
