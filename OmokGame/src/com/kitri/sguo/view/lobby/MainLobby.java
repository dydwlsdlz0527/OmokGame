package com.kitri.sguo.view.lobby;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kitri.sguo.model.login.MemberDAO;

//로그인 후 들어가는 방
//게임 대기 방
public class MainLobby extends JFrame{
	
	private MemberDAO mdao = new MemberDAO();
	
	public MainLobby(String memberid) {
		
		setVisible(true);
		setSize(1000,1000/4*3);
		setResizable(false);
		getContentPane().setEnabled(false);
		getContentPane().setLayout(null);
				
		JPanel userInfo = new UserInfoP(mdao.getUserInfo(memberid));
		userInfo.setBounds(0, 0, 363, 277);
		getContentPane().add(userInfo);
		
		JPanel gameRooms = new JPanel();
		gameRooms.setBounds(364, 0, 644, 277);
		getContentPane().add(gameRooms);
		
		JPanel rankview = new JPanel();
		rankview.setBounds(0, 462, 363, 260);
		getContentPane().add(rankview);
		
		JPanel lobbyBtn = new JPanel();
		lobbyBtn.setBounds(0, 275, 363, 187);
		getContentPane().add(lobbyBtn);
		
		JPanel chattingview = new JPanel();
		chattingview.setBounds(363, 275, 631, 447);
		getContentPane().add(chattingview);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
