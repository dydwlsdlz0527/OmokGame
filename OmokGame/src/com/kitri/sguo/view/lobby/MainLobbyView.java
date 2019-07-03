package com.kitri.sguo.view.lobby;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.kitri.sguo.controller.lobby.LobbyController;
import com.kitri.sguo.model.login.MemberDAO;

//로그인 후 들어가는 방
//게임 대기 방
public class MainLobbyView extends JFrame{
	
	MemberDAO mdao = new MemberDAO();
	MainBtnsP lobbyBtns;
	JPanel rankview;
	UserInfoP userInfo;
	JPanel gameRooms;
	
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
		
		gameRooms = new JPanel(new GridLayout(10,2));
		JScrollPane png = new JScrollPane(gameRooms);
		png.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		png.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		gameRooms.setBackground(Color.ORANGE);
		gameRooms.setForeground(Color.BLACK);
		png.setBounds(362, 0, 630, 278);
		getContentPane().add(png);
		
		
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
