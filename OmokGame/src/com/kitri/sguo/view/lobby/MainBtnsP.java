package com.kitri.sguo.view.lobby;

import javax.swing.JPanel;

import com.kitri.sguo.controller.lobby.LobbyController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainBtnsP extends JPanel{
	
	JButton makeroombtn;
	JButton sendmessagebtn;
    JButton gameinfo;
	JButton exitbtn;
	JButton gamewithcombtn;

	public MainBtnsP() {
		setBackground(Color.ORANGE);
		setLayout(null);
		
		makeroombtn = new JButton("\uBC29\uB9CC\uB4E4\uAE30");
		makeroombtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		makeroombtn.setBounds(23, 33, 93, 50);
		add(makeroombtn);
		
		sendmessagebtn = new JButton("1:1 \uCABD\uC9C0");
		sendmessagebtn.setBounds(143, 33, 93, 50);
		add(sendmessagebtn);
		
		gameinfo = new JButton("\uAC8C\uC784\uC815\uBCF4");
		gameinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		gameinfo.setBounds(258, 33, 93, 50);
		add(gameinfo);
		
		exitbtn = new JButton("\uB098\uAC00\uAE30");
		exitbtn.setBounds(143, 115, 93, 50);
		add(exitbtn);
		
		gamewithcombtn = new JButton("\uD63C\uC790\uD558\uAE30");
		gamewithcombtn.setBounds(23, 115, 93, 50);
		add(gamewithcombtn);
		
	}
	

}
