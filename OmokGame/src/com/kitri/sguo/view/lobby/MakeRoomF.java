package com.kitri.sguo.view.lobby;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.kitri.sguo.model.login.MemberDAO;

public class MakeRoomF extends JFrame{
	private JTextField textField;
	private JRadioButton rd1;
	private JRadioButton rd2;
	private JRadioButton rd3;
	private JRadioButton rd4;
	private JLabel userlevel;
	private JButton makeroombtn;
	private JButton cancelroom;
	private String userid;
	
	BufferedReader in;
	BufferedWriter out;
	
	public MakeRoomF(String userid, MainLobbyView mainlobbyview) {

		this.userid = userid;
		setTitle("\uAC8C\uC784\uBC29 \uB9CC\uB4E4\uAE30");
		getContentPane().setLayout(null);
		
		JLabel roomtitle = new JLabel("\uAC8C\uC784\uBC29 \uC774\uB984");
		roomtitle.setFont(new Font("����", Font.PLAIN, 15));
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
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rd1);
		bg.add(rd2);
		bg.add(rd3);
		bg.add(rd4);
		
		userlevel = new JLabel("\uC81C\uD55C \uB4F1\uAE09");
		userlevel.setFont(new Font("����", Font.PLAIN, 15));
		userlevel.setBounds(82, 94, 62, 32);
		getContentPane().add(userlevel);
		
		makeroombtn = new JButton("\uBC29 \uB9CC\uB4E4\uAE30");
		makeroombtn.setFont(new Font("����", Font.BOLD, 12));
		makeroombtn.setBounds(272, 167, 97, 50);
		getContentPane().add(makeroombtn);
		
		cancelroom = new JButton("\uCDE8\uC18C");
		cancelroom.setFont(new Font("����", Font.BOLD, 12));
		cancelroom.setBounds(163, 167, 97, 50);
		getContentPane().add(cancelroom);
		
		setSize(489, 309);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		makeroombtn.addActionListener(new ActionListener() {
			
			MemberDAO mdao = new MemberDAO();
			List<Object> list = mdao.getUserRoomInfo(userid);
			@Override
			public void actionPerformed(ActionEvent e) {		
				mainlobbyview.gameRooms.add(new RoomsP());
			}
		});
	}
}
