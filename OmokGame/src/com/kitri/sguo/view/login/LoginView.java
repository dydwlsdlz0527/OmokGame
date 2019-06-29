package com.kitri.sguo.view.login;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.kitri.sguo.controller.login.LoginController;

public class LoginView extends JFrame {

	private JTextField idtxtField;
	private JPasswordField passwordField;
	private JButton loginbtn;
	private JButton findidbtn;
	private JButton findpwbtn;
	private JButton joinbtn;
	

	public LoginView() {
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

		findidbtn = new JButton("���̵�ã��");
			
		findidbtn.setBounds(356, 462, 90, 23);
		getContentPane().add(findidbtn);

		findpwbtn = new JButton("��й�ȣã��");
		findpwbtn.setBounds(455, 462, 90, 23);
		getContentPane().add(findpwbtn);

		joinbtn = new JButton("ȸ������");

		joinbtn.setBounds(356, 487, 189, 23);
		getContentPane().add(joinbtn);
		
		JPanel mainimgpanel = new JPanel();
		mainimgpanel.setBounds(353, 213, 298, 195);
		getContentPane().add(mainimgpanel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(356, 438, 189, 22);
		getContentPane().add(passwordField);
		
		JButton exitbtn = new JButton("������");
		exitbtn.setBounds(550, 464, 97, 46);
		getContentPane().add(exitbtn);
		
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
					lv.findpwbtn.addActionListener(lcontroller);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
