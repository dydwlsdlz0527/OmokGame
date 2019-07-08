package com.kitri.sguo.view.lobby;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kitri.sguo.model.game.GameUser;
import com.kitri.sguo.net.client.ClientSocket;
import com.kitri.sguo.net.constdata.SguoConst;
import com.kitri.sguo.view.game.GameView;

//게임방 만들기
public class RoomsP extends JPanel{
	
	String userid;
	JButton roombtn;
	JLabel roomlimit;
	JLabel roomrank;
	JLabel roomtitle;
	Socket socket;
	int roomnum;
	GameUser user;
	GameView room;
	List<Object> playerinfo;
	
	public RoomsP() {
		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(630,100));
		setLayout(new GridLayout(1, 1));
		
		JLabel roomintro = new JLabel("                            \uC2A4\uAD6C\uC624 \uD504\uB85C\uC81D\uD2B8 \uB300\uAE30\uBC29");
		roomintro.setBackground(Color.ORANGE);
		roomintro.setFont(new Font("굴림", Font.BOLD, 20));
		add(roomintro);
	}

	public RoomsP(int roomnum, String RoomTitle, String userid,  String userrankname, String limitrank) {
		this.roomnum = roomnum;
		this.userid = userid;
		setLayout(new GridLayout(4, 1));
		roomtitle = new JLabel("       \uBC29 \uC81C\uBAA9 : " + RoomTitle);
		add(roomtitle);
		roomrank = new JLabel("       \uBC29\uC7A5 \uB4F1\uAE09 : " + userrankname);
		add(roomrank);
		roomlimit = new JLabel("       \uC81C\uD55C \uB4F1\uAE09 : " + limitrank);
		add(roomlimit);
		roombtn = new JButton("\uC785\uC7A5\uD558\uAE30");
		add(roombtn);
		setSize(315,139);
		System.out.println("ROOMSP에 방 번호 :" + roomnum);
	
		roombtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "입장하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					
//					MemberDAO mdao = new MemberDAO();
//					playerinfo = mdao.getUserInfo(userid);
//					double total = (int)playerinfo.get(1)+(int)playerinfo.get(2)+(int)playerinfo.get(3);
//					user = new GameUser(String.valueOf(roomnum), String.valueOf(playerinfo.get(4)), String.valueOf(playerinfo.get(0)), String.valueOf(total));
//					room = room.getRoom(String.valueOf(roomnum));
//					room.enterUser(user);
//					
//					room.getowner();
					searchRoom();
				}
			}
		});
	}
	
	void send(String data) {
		Thread thread = new Thread() {
			public void run() {
				try {
					socket = ClientSocket.getSocket();
					System.out.println("서버에 보내는 데이터 : " + data);
					byte[] byteArr = data.getBytes("UTF-8");
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(byteArr);
					outputStream.flush();
				} catch (Exception e) {
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
	
	void searchRoom() {
		int goroomnum = roomnum;
		System.out.println("선택한 방 번호 : " + goroomnum);
		//System.out.println(room.getroomnum()+" : 선택한 방 번호 2" + room.getRoomId());
		//room.setVisible(true);
		send(SguoConst.GOGAME+"||"+goroomnum);
	}
	
	
}
