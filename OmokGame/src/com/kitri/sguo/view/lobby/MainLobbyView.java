package com.kitri.sguo.view.lobby;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kitri.sguo.controller.lobby.LobbyController;
import com.kitri.sguo.model.login.MemberDAO;
import java.awt.Color;

//로그인 후 들어가는 방
//게임 대기 방
public class MainLobbyView extends JFrame{
	
	private MemberDAO mdao = new MemberDAO();
	public MainBtnsP lobbyBtns;
	private JPanel rankview;
	private UserInfoP userInfo;
	
	public MainLobbyView(String memberid) {
		getContentPane().setBackground(Color.ORANGE);
		setBackground(Color.ORANGE);
		setResizable(false);
		getContentPane().setForeground(Color.ORANGE);
		
		setVisible(true);
		setSize(1000,1000/4*3);
		getContentPane().setEnabled(false);
		getContentPane().setLayout(null);
				
		userInfo = new UserInfoP(mdao.getUserInfo(memberid));
		userInfo.setBackground(Color.ORANGE);
		userInfo.setBounds(0, 0, 365, 277);
		getContentPane().add(userInfo);
		
		JPanel gameRooms = new RoomsP();
		gameRooms.setBackground(Color.ORANGE);
		gameRooms.setForeground(Color.BLACK);
		gameRooms.setBounds(363, 0, 630, 277);
		getContentPane().add(gameRooms);
		
		rankview = new JPanel();
		rankview.setBackground(Color.ORANGE);
		rankview.setBounds(0, 462, 363, 260);
		getContentPane().add(rankview);
		
		lobbyBtns = new MainBtnsP();
		lobbyBtns.setForeground(Color.ORANGE);
		lobbyBtns.setBounds(0, 275, 363, 187);
		getContentPane().add(lobbyBtns);
		
		JPanel chattingview = new JPanel();
		chattingview.setBackground(Color.ORANGE);
		chattingview.setBounds(363, 275, 630, 447);
		getContentPane().add(chattingview);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void MakeLobbyController() {
		LobbyController lobbycontroller = new LobbyController(this.lobbyBtns);
		lobbyBtns.exitbtn.addActionListener(lobbycontroller);
		lobbyBtns.sendmessagebtn.addActionListener(lobbycontroller);
		lobbyBtns.gameinfo.addActionListener(lobbycontroller);
		lobbyBtns.gamewithcombtn.addActionListener(lobbycontroller);
		lobbyBtns.makeroombtn.addActionListener(lobbycontroller);
		
		LobbyController lobbycontroller2 = new LobbyController(this, this.userInfo, userInfo.getUserId.getText());
		userInfo.userModified.addActionListener(lobbycontroller2);
		userInfo.secession.addActionListener(lobbycontroller2);
	}
	
}
