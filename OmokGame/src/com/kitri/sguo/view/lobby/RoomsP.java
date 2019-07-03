package com.kitri.sguo.view.lobby;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

//게임방 만들기
public class RoomsP extends JPanel{
	private JPanel panel;
	private JScrollPane png;

	public RoomsP() {
		
		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		png = new JScrollPane(panel);
		png.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		png.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel jp = new JPanel();
		jp.setSize(panel.getWidth()/2, panel.getHeight()/2);
		jp.setBackground(Color.PINK);
		panel.add(jp);
			
	}
}
