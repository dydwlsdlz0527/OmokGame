package com.kitri.sguo.view.lobby;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserInfoP extends JPanel{
	
	JButton secession;
	JButton userModified;
	JLabel getUserId;
	BufferedImage bi;

	public UserInfoP(List<Object> ulist) {
		//È¸¿ø Á¤º¸ °®°í ¿À±â

		setLayout(null);
		setBounds(0, 0, 342, 277);
		
		JLabel userid = new JLabel(" \uC544\uC774\uB514 : ");
		userid.setFont(new Font("±¼¸²", Font.BOLD, 14));
		userid.setBounds(180, 31, 69, 27);
		add(userid);
		
		getUserId = new JLabel(ulist.get(0)+"");
		getUserId.setFont(new Font("±¼¸²", Font.BOLD, 14));
		getUserId.setBounds(252, 31, 69, 27);
		add(getUserId);
		
		JLabel logs = new JLabel("\uC804\uC801");
		logs.setFont(new Font("±¼¸²", Font.BOLD, 14));
		logs.setBounds(188, 68, 69, 27);
		add(logs);
		
		JLabel getWin = new JLabel(ulist.get(1)+"");
		getWin.setFont(new Font("±¼¸²", Font.BOLD, 14));
		getWin.setBounds(188, 105, 30, 27);
		add(getWin);
		
		JLabel winl = new JLabel("\uC2B9");
		winl.setFont(new Font("±¼¸²", Font.BOLD, 14));
		winl.setBounds(217, 105, 30, 27);
		add(winl);

		JLabel getDraw = new JLabel(ulist.get(2)+"");
		getDraw.setFont(new Font("±¼¸²", Font.BOLD, 14));
		getDraw.setBounds(235, 105, 30, 27);
		add(getDraw);
		
		JLabel drawl = new JLabel("\uBB34");
		drawl.setFont(new Font("±¼¸²", Font.BOLD, 14));
		drawl.setBounds(263, 105, 30, 27);
		add(drawl);
		
		JLabel getLose = new JLabel(ulist.get(3)+"");
		getLose.setFont(new Font("±¼¸²", Font.BOLD, 14));
		getLose.setBounds(283, 105, 30, 27);
		add(getLose);
		
		JLabel losel = new JLabel("\uD328");
		losel.setFont(new Font("±¼¸²", Font.BOLD, 14));
		losel.setBounds(312, 105, 30, 27);
		add(losel);
		
		JLabel shiftl = new JLabel("\uC2B9\uB960");
		shiftl.setFont(new Font("±¼¸²", Font.BOLD, 14));
		shiftl.setBounds(188, 142, 69, 27);
		add(shiftl);
		
		//½Â·ü °è»ê
		ulist.get(1);
		ulist.get(2);
		ulist.get(3);
		double total = (int)ulist.get(1)+(int)ulist.get(2)+(int)ulist.get(3);
		double nshift = 0;
		if(total==0) {
			nshift = 0;
		}else {
			nshift = (int)ulist.get(1)/total * 100;
		}
		JLabel shift = new JLabel(String.valueOf(nshift));
		shift.setFont(new Font("±¼¸²", Font.BOLD, 14));
		shift.setBounds(222, 175, 72, 27);
		add(shift);
		
		JLabel shift2 = new JLabel("%");
		shift2.setFont(new Font("±¼¸²", Font.BOLD, 14));
		shift2.setBounds(265, 175, 72, 27);
		add(shift2);
		
		bi = (BufferedImage) ulist.get(4);
		JPanel imagepanel = new LobbyUserImageP(bi);
		imagepanel.setBounds(12, 10, 164, 257);
		imagepanel.setVisible(true);
		add(imagepanel);
		
		
		userModified = new JButton("\uC218\uC815");
		userModified.setFont(new Font("±¼¸²", Font.BOLD, 12));
		userModified.setBounds(185, 227, 72, 34);
		add(userModified);
		
		secession = new JButton("\uD0C8\uD1F4");
		secession.setFont(new Font("±¼¸²", Font.BOLD, 12));
		secession.setBounds(265, 227, 69, 34);
		add(secession);
			
	}
}
