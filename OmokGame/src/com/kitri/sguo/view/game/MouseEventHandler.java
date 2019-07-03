package com.kitri.sguo.view.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JFrame;

public class MouseEventHandler extends MouseAdapter {
	int PW = 123;

	String m = null;
	String n = null;
	private Map map;
	private MapSize ms;
	private DrawBoard d;
	private GameView gameview;

	String a;
	String b;
	String temp;

	public MouseEventHandler(Map m, MapSize ms, DrawBoard d, GameView gameview) {
		map = m;
		this.ms = ms;
		this.d = d;
		this.gameview = gameview;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		int x = (int) Math.round(e.getX() / (double) ms.getCell()) - 1;
		int y = (int) Math.round(e.getY() / (double) ms.getCell()) - 2;
		if (x < 0 || x > 19 || y < 0 || y > 19) {
			return;
		}

		if (map.getXY(y, x) == map.getBlack() || map.getXY(y, x) == map.getWhite()) { // 있으면 못놓음
			return;
		}
		System.out.println(y + " " + x);
		map.setMap(y, x);
		map.changeCheck();
		d.repaint();

		b = Integer.toString(y);
		System.out.print(b + ", ");
		//send(b);
		a = Integer.toString(x);
		//send(a);
		System.out.println("바둑 위치 : " + b + ", " + a);
		if (map.winCheck(x, y)) {
			if (map.getCheck() == true) {
				gameview.showPopUp("백돌이 승리했습니다.");
			} else {
				gameview.showPopUp("흑돌이 승리했습니다.");
			}
		}
	}

//	void send(String data) {
//
//		Thread thread = new Thread() {
//			@Override
//			public void run() {
//
//				try {
//					byte[] Arr = data.getBytes("UTF-8");
//					
//					OutputStream outputStream = socket.getOutputStream();
//					outputStream.write(Arr);
//					
//					System.out.println("서버로 데이터 전송");
//					outputStream.flush();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					// stopClient();
//				}
//
//			}
//		};
//		thread.start();
//	}

}
