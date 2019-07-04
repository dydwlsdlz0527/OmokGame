package com.kitri.sguo.view.lobby;


import javax.swing.JPanel;

import com.kitri.sguo.view.game.GameView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

//게임방 만들기
public class RoomsP extends JPanel{
	
	String userid;
	private JButton roombtn;
	private JLabel roomlimit;
	private JLabel roomrank;
	private JLabel roomtitle;
	
	public RoomsP() {
		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(630,100));
		setLayout(new GridLayout(1, 1));
		
		JLabel roomintro = new JLabel("                            \uC2A4\uAD6C\uC624 \uD504\uB85C\uC81D\uD2B8 \uB300\uAE30\uBC29");
		roomintro.setBackground(Color.ORANGE);
		roomintro.setFont(new Font("굴림", Font.BOLD, 20));
		add(roomintro);
	}

	public RoomsP(String userid, String RoomTitle, String userrankname, String limitrank) {
		
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
	
		roombtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "입장하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					//예를 선택할 경우.
					new GameView();
				}
			}
		});
	}
}
