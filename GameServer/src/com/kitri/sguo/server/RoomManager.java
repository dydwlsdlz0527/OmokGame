package com.kitri.sguo.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomManager {
	
	public static List<GameRoom> roomList;
	public static AtomicInteger atomicInteger;
	
	static {
		roomList = new ArrayList<>();
		atomicInteger = new AtomicInteger();
	}
	
	public RoomManager() {}
	
	public static GameRoom createRoom() {
		//���ӹ��� ���� �����ϸ� �� ��ȣ�� ������.
		int roomid = atomicInteger.incrementAndGet();
		GameRoom room = new GameRoom(roomid);
		roomList.add(room);
		System.out.println("���ο� ���� ������.");
		
		return room;
	}
	
	public static GameRoom createRoom(GameUser owner) {
		int roomid = atomicInteger.incrementAndGet();
		GameRoom room = new GameRoom(roomid);
		room.enterUser(owner);
		room.setOwner(owner);
		roomList.add(room);
		System.out.println("���ο� �� ����!!");
		return room;
	}
	
	public static GameRoom getRoom(GameRoom gameRoom) {
		
		int idx = roomList.indexOf(gameRoom);
		if(idx>0) {
			return roomList.get(idx);
		}else {
			return null;
		}
	}
	
	public static void removeRoom(GameRoom room) {
		room.close();
		roomList.remove(room);
		System.out.println("�ش� �� ����.");
	}
	
	public static int roomCount() {
		return roomList.size();
	}

}
