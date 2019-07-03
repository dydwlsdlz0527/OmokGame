package com.kitri.sguo.view.lobby;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

//게임방 만들기
public class RoomsP extends JPanel{
	private JPanel panel;
	private JScrollPane png;

	public RoomsP() {
		setBackground(Color.YELLOW);
		setLayout(new GridLayout(4, 1));
		
		JLabel label = new JLabel("      \uBC29 \uC81C\uBAA9 : ");
		add(label);
		
		JLabel lblNewLabel = new JLabel("      \uBC29\uC7A5 \uB4F1\uAE09 : ");
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("      \uC81C\uD55C \uB4F1\uAE09 : ");
		add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\uC785\uC7A5\uD558\uAE30");
		btnNewButton.setBackground(Color.RED);
		add(btnNewButton);
			
	}
}
