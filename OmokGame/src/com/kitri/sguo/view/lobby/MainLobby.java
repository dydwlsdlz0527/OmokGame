package com.kitri.sguo.view.lobby;

import javax.swing.JFrame;

//로그인 후 들어가는 방
//게임 대기 방
public class MainLobby extends JFrame{
	
	public MainLobby() {
		setVisible(true);
		setSize(1000,1000/4*3);
		setResizable(false);
		getContentPane().setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
	}
	
	
}
