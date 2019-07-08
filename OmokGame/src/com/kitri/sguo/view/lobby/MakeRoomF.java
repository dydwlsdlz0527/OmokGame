package com.kitri.sguo.view.lobby;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.kitri.sguo.model.game.GameUser;
import com.kitri.sguo.model.game.RoomManager;
import com.kitri.sguo.model.login.MemberDAO;
import com.kitri.sguo.net.client.ClientSocket;
import com.kitri.sguo.net.constdata.SguoConst;

public class MakeRoomF extends JFrame {

	JTextField textField;
	JRadioButton rd1;
	JRadioButton rd2;
	JRadioButton rd3;
	JRadioButton rd4;
	JLabel userlevel;
	JButton makeroombtn;
	JButton cancelroom;
	String userid;
	MainLobbyView mainlobbyview;
	String limitrank;
	BufferedReader in;
	BufferedWriter out;
	Socket socket;
	ButtonGroup bg;
	String userrankname;
	int roomnum;
	RoomManager roommanager;
	List<Object> ownerinfo;
	GameUser owner;

	public MakeRoomF(String userid, MainLobbyView mainlobbyview) {

		this.mainlobbyview = mainlobbyview;
		this.userid = userid;
		setTitle("\uAC8C\uC784\uBC29 \uB9CC\uB4E4\uAE30");
		getContentPane().setLayout(null);

		JLabel roomtitle = new JLabel("\uAC8C\uC784\uBC29 \uC774\uB984");
		roomtitle.setFont(new Font("굴림", Font.PLAIN, 15));
		roomtitle.setBounds(70, 44, 75, 32);
		getContentPane().add(roomtitle);

		textField = new JTextField();
		textField.setBounds(164, 38, 204, 38);
		getContentPane().add(textField);
		textField.setColumns(10);

		rd1 = new JRadioButton("\uD558\uC218");
		rd1.setBounds(164, 99, 54, 23);
		getContentPane().add(rd1);

		rd2 = new JRadioButton("\uC911\uC218");
		rd2.setBounds(222, 99, 54, 23);
		getContentPane().add(rd2);

		rd3 = new JRadioButton("\uACE0\uC218");
		rd3.setBounds(280, 99, 54, 23);
		getContentPane().add(rd3);

		rd4 = new JRadioButton("\uC544\uBB34\uB098");
		rd4.setBounds(338, 99, 75, 23);
		getContentPane().add(rd4);

		bg = new ButtonGroup();
		bg.add(rd1);
		bg.add(rd2);
		bg.add(rd3);
		bg.add(rd4);

		userlevel = new JLabel("\uC81C\uD55C \uB4F1\uAE09");
		userlevel.setFont(new Font("굴림", Font.PLAIN, 15));
		userlevel.setBounds(82, 94, 62, 32);
		getContentPane().add(userlevel);

		makeroombtn = new JButton("\uBC29 \uB9CC\uB4E4\uAE30");
		makeroombtn.setFont(new Font("굴림", Font.BOLD, 12));
		makeroombtn.setBounds(272, 167, 97, 50);
		getContentPane().add(makeroombtn);

		cancelroom = new JButton("\uCDE8\uC18C");
		cancelroom.setFont(new Font("굴림", Font.BOLD, 12));
		cancelroom.setBounds(163, 167, 97, 50);
		getContentPane().add(cancelroom);

		setSize(489, 309);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		makeroombtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 선택된 라디오 버튼 가져오기
				if (rd1.isSelected()) {
					limitrank = rd1.getText();
				} else if (rd2.isSelected()) {
					limitrank = rd2.getText();
				} else if (rd3.isSelected()) {
					limitrank = rd3.getText();
				} else {
					limitrank = rd4.getText();
				}
				MemberDAO mdao = new MemberDAO();
				userrankname = mdao.getUserRoomInfo(userid);
				if (textField.getText().trim() == null || limitrank.isEmpty()) {
					JOptionPane.showMessageDialog(null, "작성 내용을 다시 확인하세요.");
				} else {
					socket = ClientSocket.getSocket();
					// 게임방 패널 보이게 만들기.
					// 방만들기포트 || 방제 || 아이디 || 방장 등급 || 제한 등급
					String data1 = SguoConst.MRPROT + "||" + textField.getText() + "||" + userid + "||" + userrankname
							+ "||" + limitrank;
					System.out.println("게임방 만들 때 서버에 보내는 데이터 : " + data1);
					send(data1);

					// 진짜 게임방을 만들고 안에서 방장의 정보 출력.
					// 방장의 이미지 || 아이디 || 승률
					System.out.println("send1 이후");
					ownerinfo = mdao.getUserInfo(userid);
					double total = (int) ownerinfo.get(1) + (int) ownerinfo.get(2) + (int) ownerinfo.get(3);
					double shift = 0.0;
					if(total==0) {
						shift = 0.0;
					}else{
						shift = (int) ownerinfo.get(1) / total * 100;
					}
					String data2 = SguoConst.GOGAME + "||" + ownerinfo.get(4) + "||" + ownerinfo.get(0) + "||" + shift;
					send(data2);
					mainlobbyview.png.getVerticalScrollBar().setValue(mainlobbyview.png.getVerticalScrollBar().getMinimum() + 10);
					mainlobbyview.gameRooms.repaint();
					dispose();
				}
			}
		});
		// 취소
		cancelroom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	void send(String data) {
		Thread thread = new Thread() {
			public void run() {
				try {
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

	void MakeGameRoom() {

	}

}
