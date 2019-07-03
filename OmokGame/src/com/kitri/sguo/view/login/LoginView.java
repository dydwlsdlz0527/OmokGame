package com.kitri.sguo.view.login;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.kitri.sguo.controller.login.LoginController;
import java.awt.Font;

public class LoginView extends JFrame {

	private JTextField idtxtField;
	private JPasswordField passwordField;
	private JButton loginbtn;
	private JButton findidpwbtn;
	private JButton joinbtn;
	private JButton exitbtn;
	private JPanel mainimgpanel;
	private Image img;
	private BufferedImage bi;
	
	public LoginView() {
		getContentPane().setBackground(Color.ORANGE);
		//����ȭ�� ����
		setVisible(true);
		setSize(1000,1000/4*3);
		setResizable(false);
		getContentPane().setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(340, 418, 57, 15);
		getContentPane().add(idLabel);

		idtxtField = new JTextField();
		idtxtField.setBounds(356, 415, 189, 21);
		getContentPane().add(idtxtField);
		idtxtField.setColumns(10);

		JLabel pwLabel = new JLabel("Password");
		pwLabel.setBounds(291, 441, 63, 15);
		getContentPane().add(pwLabel);

		loginbtn = new JButton("�α���");

		loginbtn.setBounds(550, 415, 97, 44);
		getContentPane().add(loginbtn);

		findidpwbtn = new JButton("\uC544\uC774\uB514/\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		findidpwbtn.setBounds(356, 462, 189, 23);
		getContentPane().add(findidpwbtn);

		joinbtn = new JButton("ȸ������");

		joinbtn.setBounds(356, 487, 189, 23);
		getContentPane().add(joinbtn);
		
		//���� �̹��� �о����
		try {
			bi = ImageIO.read(new File("image/omokimage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image img = bi.getScaledInstance(bi.getWidth(),bi.getHeight(), Image.SCALE_SMOOTH);
		mainimgpanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img,0,0,null);
				g.dispose();
				setOpaque(false);
			}
		};
		mainimgpanel.setBackground(Color.ORANGE);
		mainimgpanel.setBounds(152, 112, 302, 280);
		//mainimgpanel.setVisible(true);
		getContentPane().add(mainimgpanel);
		mainimgpanel.setVisible(true);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(356, 438, 189, 22);
		getContentPane().add(passwordField);
		
		exitbtn = new JButton("������");
		exitbtn.setBounds(550, 464, 97, 46);
		getContentPane().add(exitbtn);
		
		JLabel gameTitle = new JLabel("Swing\uC73C\uB85C \uAD6C\uD604\uD55C \uC624\uBAA9 \uAC8C\uC784");
		gameTitle.setFont(new Font("�ü�", Font.BOLD, 30));
		gameTitle.setBounds(466, 156, 455, 93);
		getContentPane().add(gameTitle);
		
		JLabel gameTitle2 = new JLabel("\uC2A4\uAD6C\uC624 Project");
		gameTitle2.setFont(new Font("�ü�", Font.BOLD, 30));
		gameTitle2.setBounds(466, 216, 336, 93);
		getContentPane().add(gameTitle2);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginView lv = new LoginView();
					//��Ʈ�ѷ� ��ü ����
					LoginController lcontroller = new LoginController(lv.idtxtField, lv.passwordField, lv);
					//�α��� ��ư Ŭ��
					lv.loginbtn.addActionListener(lcontroller);
					//ȸ������ ��ư Ŭ��
					lv.joinbtn.addActionListener(lcontroller);
					//��й�ȣ ã�� ��ư Ŭ��
					lv.findidpwbtn.addActionListener(lcontroller);
					//������ ��ư Ŭ��
					lv.exitbtn.addActionListener(lcontroller);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
