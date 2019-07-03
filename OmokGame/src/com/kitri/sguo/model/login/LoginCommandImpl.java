package com.kitri.sguo.model.login;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kitri.sguo.net.client.UserSoc;
import com.kitri.sguo.view.lobby.MainLobbyView;
import com.kitri.sguo.view.login.DialogPhoneNum;
import com.kitri.sguo.view.login.JoinView;
import com.kitri.sguo.view.login.LoginView;

public class LoginCommandImpl implements LoginCommand {

	MemberDAO mdao = new MemberDAO();
	private LoginView loginview;
	private UserSoc usersoc;

	public LoginCommandImpl(LoginView loginview) {
		this.loginview = loginview;
	}

	@Override
	public void join() {
		JoinView jv = new JoinView();
		jv.MakeJoinController();
	}

	@Override
	public void findidpassword(LoginView loginview) {
		new DialogPhoneNum(loginview);
	}

	@Override
	public void login(JTextField user_id, JPasswordField user_password) {
		if (mdao.login(user_id, user_password)) {
			JOptionPane.showMessageDialog(null, "�α��� ����");
			loginview.setVisible(false);
			MainLobbyView ml = new MainLobbyView(user_id.getText());
			ml.MakeLobbyController();
			ml.setVisible(true);
			// ����� ������ ���� �ν��Ͻ� ����.
			usersoc = new UserSoc(user_id.getText());
		} else {
			JOptionPane.showMessageDialog(null, "�α��� ����");
		}
	}

	// ���α׷� ����
	@Override
	public void exit() {
		System.exit(0);
	}

}
