package com.kitri.sguo.view.login;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.kitri.sguo.controller.join.JoinController;

public class JoinView extends JFrame{
	
	private JTextField idtxt;
	private JTextField passwordtxt;
	private JTextField passwordchktxt;
	private JTextField anstxtfield;
	private JComboBox comboBox;
	private JTextArea textArea;
	private JButton idcheckbtn;
	private JButton joinbtn;
	private int frameclosenum = 1;

	public JoinView() {
		setSize(402,538);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setEnabled(false);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setVisible(true);
		
		Dialog d = new Dialog(this,"회원 가입");
		getContentPane().setLayout(null);
		d.setResizable(false);
		JLabel joinlb = new JLabel("\uD68C\uC6D0 \uAC00\uC785");
		joinlb.setFont(new Font("나눔고딕", Font.BOLD, 23));
		joinlb.setBounds(123, 10, 168, 56);
		getContentPane().add(joinlb);
		
		JLabel useridlb = new JLabel("\uC544\uC774\uB514");
		useridlb.setBounds(52, 80, 102, 56);
		getContentPane().add(useridlb);
		
		idtxt = new JTextField();
		idtxt.setBounds(93, 94, 168, 29);
		getContentPane().add(idtxt);
		idtxt.setColumns(10);
		
		idcheckbtn = new JButton("중복확인");
		idcheckbtn.setBounds(273, 94, 85, 29);
		getContentPane().add(idcheckbtn);
		
		JLabel userpasswordlb = new JLabel("\uBE44\uBC00\uBC88\uD638");
		userpasswordlb.setBounds(40, 119, 102, 56);
		getContentPane().add(userpasswordlb);
		
		passwordtxt = new JTextField();
		passwordtxt.setColumns(10);
		passwordtxt.setBounds(93, 133, 168, 29);
		getContentPane().add(passwordtxt);
		
		passwordchktxt = new JTextField();
		passwordchktxt.setColumns(10);
		passwordchktxt.setBounds(93, 171, 168, 29);
		getContentPane().add(passwordchktxt);
		
		JLabel passworkdchklb = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		passworkdchklb.setBounds(12, 157, 102, 56);
		getContentPane().add(passworkdchklb);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		
		JLabel hintlb = new JLabel("\uD78C\uD2B8");
		toolBar.add(hintlb);
		toolBar.addSeparator();
		toolBar.setBounds(49, 214, 212, 29);
		getContentPane().add(toolBar);
		comboBox = new JComboBox(new String[] {"어렸을적 별명은?","다니던 초등학교 이름은?","키우는 애완동물 이름은?"});
		toolBar.add(comboBox);
		
		anstxtfield = new JTextField();
		anstxtfield.setColumns(10);
		anstxtfield.setBounds(93, 253, 168, 29);
		getContentPane().add(anstxtfield);
		
		JLabel hintanslb = new JLabel("\uC815\uB2F5");
		hintanslb.setBounds(62, 260, 57, 15);
		getContentPane().add(hintanslb);
		
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(93, 292, 168, 92);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);
		
		JLabel introlb = new JLabel("\uC790\uAE30\uC18C\uAC1C");
		introlb.setBounds(38, 293, 57, 15);
		getContentPane().add(introlb);
		
		JButton cancelbtn = new JButton("취소");
		cancelbtn.setBounds(93, 394, 80, 39);
		getContentPane().add(cancelbtn);
		
		joinbtn = new JButton("가입");
		joinbtn.setForeground(Color.BLACK);
		joinbtn.setBounds(181, 394, 80, 39);
		getContentPane().add(joinbtn);
		
//		JoinController jcontroller = new JoinController(idtxt,passwordtxt, passwordchktxt, comboBox, anstxtfield, textArea, frameclosenum, joinbtn);
//		idcheckbtn.addActionListener(jcontroller);
//		joinbtn.addActionListener(jcontroller);
	}
	
	public void joinControllerMake(JoinView joinview) {
		JoinController jcontroller = new JoinController(idtxt,passwordtxt, passwordchktxt, comboBox, anstxtfield, textArea, frameclosenum, joinview);
		idcheckbtn.addActionListener(jcontroller);
		joinbtn.addActionListener(jcontroller);
	}
	
}
