package com.kitri.sguo.view.login;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class FindPassword extends Frame{

	private JDialog d;
	
	public FindPassword() {
		System.out.println("findpassword������");
		JFrame f = new JFrame();
		d = new JDialog(f,"��й�ȣ ã��",true);
		d.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		d.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel useridlb = new JLabel("���̵�");
		useridlb.setBounds(0, 0, 57, 15);
		panel.add(useridlb);
		f.getContentPane().setLayout(null);
		
		d.setSize(300,300);
		d.setVisible(true);
		d.setResizable(false);
		d.setLocationRelativeTo(null);
		f.setLocationRelativeTo(null);
	}
}
