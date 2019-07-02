package com.kitri.sguo.view.login;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.kitri.sguo.controller.join.JoinController;

public class JoinView extends JFrame {
	
	JTextField idtxt;
	JPasswordField passwordtxt;
	JPasswordField passwordchktxt;
	JTextField anstxtfield;
	JComboBox comboBox;
	JTextArea textArea;
	JButton idcheckbtn;
	JButton joinbtn;
	JButton addImagebtn;
	ImageP imagep;
	JTextField phonenumtxt;
	JLabel phonenuml;
	JButton cancelbtn;
	JLabel lblNewLabel;

	public JoinView() {
		setSize(585,604);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setEnabled(false);
		//setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setVisible(true);
		//해당 프레임 창만 종료
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Dialog d = new Dialog(this,"회원 가입");
		getContentPane().setLayout(null);
		d.setResizable(false);
		
		JLabel joinlb = new JLabel("\uD68C\uC6D0 \uAC00\uC785");
		joinlb.setFont(new Font("나눔고딕", Font.BOLD, 23));
		joinlb.setBounds(251, 10, 168, 56);
		getContentPane().add(joinlb);
		
		JLabel useridlb = new JLabel("\uC544\uC774\uB514");
		useridlb.setBounds(246, 80, 102, 56);
		getContentPane().add(useridlb);
		
		idtxt = new JTextField();
		idtxt.setBounds(289, 94, 168, 29);
		getContentPane().add(idtxt);
		idtxt.setColumns(10);
		
		idcheckbtn = new JButton("중복확인");
		idcheckbtn.setBounds(469, 94, 85, 29);
		getContentPane().add(idcheckbtn);
		
		JLabel userpasswordlb = new JLabel("\uBE44\uBC00\uBC88\uD638");
		userpasswordlb.setBounds(231, 119, 102, 56);
		getContentPane().add(userpasswordlb);
		
		passwordtxt = new JPasswordField();
		passwordtxt.setColumns(10);
		passwordtxt.setBounds(289, 133, 168, 29);
		getContentPane().add(passwordtxt);
		
		passwordchktxt = new JPasswordField();
		passwordchktxt.setColumns(10);
		passwordchktxt.setBounds(289, 172, 168, 29);
		getContentPane().add(passwordchktxt);
		
		JLabel passworkdchklb = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		passworkdchklb.setBounds(207, 158, 102, 56);
		getContentPane().add(passworkdchklb);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		
		JLabel hintlb = new JLabel("\uC9C8\uBB38");
		toolBar.add(hintlb);
		toolBar.addSeparator();
		toolBar.setBounds(246, 252, 215, 29);
		getContentPane().add(toolBar);
		comboBox = new JComboBox(new String[] {"어렸을적 별명은?","다니던 초등학교 이름은?","키우는 애완동물 이름은?"});
		toolBar.add(comboBox);
		
		anstxtfield = new JTextField();
		anstxtfield.setColumns(10);
		anstxtfield.setBounds(289, 291, 168, 29);
		getContentPane().add(anstxtfield);
		
		JLabel hintanslb = new JLabel("\uC815\uB2F5");
		hintanslb.setBounds(256, 298, 57, 15);
		getContentPane().add(hintanslb);
		
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(289, 330, 168, 92);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);
		
		JLabel introlb = new JLabel("\uC790\uAE30\uC18C\uAC1C");
		introlb.setBounds(234, 331, 57, 15);
		getContentPane().add(introlb);
		
		cancelbtn = new JButton("취소");
		cancelbtn.setBounds(289, 432, 80, 39);
		getContentPane().add(cancelbtn);
		
		joinbtn = new JButton("가입");
		joinbtn.setForeground(Color.BLACK);
		joinbtn.setBounds(381, 432, 80, 39);
		getContentPane().add(joinbtn);
		
		imagep = new ImageP();
		imagep.setBounds(31, 94, 164, 257);
		getContentPane().add(imagep);
		
		addImagebtn = new JButton("\uC774\uBBF8\uC9C0\uCD94\uAC00");
		addImagebtn.setBounds(31, 368, 164, 29);
		getContentPane().add(addImagebtn);
			
		phonenumtxt = new JTextField();
		phonenumtxt.setColumns(10);
		phonenumtxt.setBounds(289, 211, 168, 29);
		getContentPane().add(phonenumtxt);
		
		phonenuml = new JLabel("\uD578\uB4DC\uD3F0\uBC88\uD638");
		phonenuml.setBounds(223, 197, 102, 56);
		getContentPane().add(phonenuml);
		
		lblNewLabel = new JLabel("\uAD8C\uC7A5- \uAC00\uB85C:164px, \uC138\uB85C:257px");
		lblNewLabel.setBounds(31, 407, 177, 29);
		getContentPane().add(lblNewLabel);

	}
	
	public void MakeJoinController() {
		JoinController jcontroller = new JoinController(idtxt, passwordtxt, passwordchktxt, phonenumtxt, comboBox, anstxtfield, textArea, this, imagep);
		idcheckbtn.addActionListener(jcontroller);
		joinbtn.addActionListener(jcontroller);
		addImagebtn.addActionListener(jcontroller);
		cancelbtn.addActionListener(jcontroller);
	}


}
