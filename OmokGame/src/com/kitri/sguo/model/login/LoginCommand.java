package com.kitri.sguo.model.login;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kitri.sguo.view.login.LoginView;

public interface LoginCommand {
	
	//ȸ������
	public abstract void join();
	//��й�ȣã��
	public abstract void findpassword();
	//���̵�ã��
	public abstract void findid();
	//�α���
	public abstract void login(JTextField user_id, JPasswordField user_password);
	
}
