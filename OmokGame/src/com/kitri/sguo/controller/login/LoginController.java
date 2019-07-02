package com.kitri.sguo.controller.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kitri.sguo.model.login.LoginCommandImpl;
import com.kitri.sguo.model.login.MemberDAO;
import com.kitri.sguo.view.login.LoginView;


public class LoginController implements ActionListener{
	
	private JTextField idtxtFiled;
	private JPasswordField passwordField;
	private MemberDAO memberDAO;
	private LoginView loginview;
	
	public LoginController(JTextField idtxtFiled, JPasswordField passwordField, LoginView loginview) {
		this.idtxtFiled = idtxtFiled;
		this.passwordField = passwordField;
		this.loginview = loginview;
	}

	public LoginController(JTextField idtxtFiled, JPasswordField passwordField, MemberDAO memberDAO) {
		this.idtxtFiled = idtxtFiled;
		this.passwordField = passwordField;
		this.memberDAO = memberDAO;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		LoginCommandImpl lci = new LoginCommandImpl(loginview);
		String cmd = e.getActionCommand();
		if(cmd.equals("�α���")) {
			lci.login(idtxtFiled,passwordField);
		}else if(cmd.equals("ȸ������")) {
			lci.join();
		}else if(cmd.equals("������")){
			lci.exit();
		}else if(cmd.equals("���̵�/��й�ȣ ã��")) {
			lci.findidpassword(loginview);
		}
		
	}


}
