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
		//게임화면 설정
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

		loginbtn = new JButton("로그인");

		loginbtn.setBounds(550, 415, 97, 44);
		getContentPane().add(loginbtn);

		findidbtn = new JButton("아이디찾기");
			
		findidbtn.setBounds(356, 462, 90, 23);
		getContentPane().add(findidbtn);

		findpwbtn = new JButton("비밀번호찾기");
		findpwbtn.setBounds(455, 462, 90, 23);
		getContentPane().add(findpwbtn);

		joinbtn = new JButton("회원가입");

		joinbtn.setBounds(356, 487, 189, 23);
		getContentPane().add(joinbtn);
		
		JPanel mainimgpanel = new JPanel();
		mainimgpanel.setBounds(353, 213, 298, 195);
		getContentPane().add(mainimgpanel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(356, 438, 189, 22);
		getContentPane().add(passwordField);
		
		JButton exitbtn = new JButton("나가기");
		exitbtn.setBounds(550, 464, 97, 46);
		getContentPane().add(exitbtn);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginView lv = new LoginView();
					//컨트롤러 객체 생성
					LoginController lcontroller = new LoginController(lv.idtxtField, lv.passwordField, lv);
					//로그인 버튼 클릭
					lv.loginbtn.addActionListener(lcontroller);
					//회원가입 버튼 클릭
					lv.joinbtn.addActionListener(lcontroller);
					//비밀번호 찾기 버튼 클릭
					lv.findpwbtn.addActionListener(lcontroller);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
