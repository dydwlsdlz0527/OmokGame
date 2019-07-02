package com.kitri.sguo.model.login;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kitri.sguo.view.login.LoginView;

public interface LoginCommand {
	
	//회원가입
	public abstract void join();
	//아이디,비밀번호찾기
	public abstract void findidpassword(LoginView loginview);
	//로그인
	public abstract void login(JTextField user_id, JPasswordField user_password);
	//나가기
	public abstract void exit();
	
}
