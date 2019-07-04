package com.kitri.sguo.view.lobby;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class RoomIntro extends JPanel{
	
	public RoomIntro() {
		setBackground(Color.ORANGE);
		setSize(630,50);
		setLayout(new GridLayout(1, 0));
		
		JLabel roomintro = new JLabel("                                         \uC2A4\uAD6C\uC624 \uD504\uB85C\uC81D\uD2B8 \uB300\uAE30\uBC29");
		roomintro.setBackground(Color.ORANGE);
		roomintro.setFont(new Font("±¼¸²", Font.BOLD, 16));
		add(roomintro);
	}

}
