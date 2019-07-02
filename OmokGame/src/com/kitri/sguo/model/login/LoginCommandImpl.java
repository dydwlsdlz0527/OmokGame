package com.kitri.sguo.model.login;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kitri.sguo.constdata.SguoConst;
import com.kitri.sguo.view.lobby.MainLobby;
import com.kitri.sguo.view.login.FindPassword;
import com.kitri.sguo.view.login.JoinView;
import com.kitri.sguo.view.login.LoginView;

public class LoginCommandImpl implements LoginCommand {

	MemberDAO mdao = new MemberDAO();
	private LoginView loginview;
	private Socket userSocket;
	
	public LoginCommandImpl(LoginView loginview) {
		this.loginview = loginview;
	}
	
	@Override
	public void join() {
		JoinView jv = new JoinView();
		jv.joinControllerMake(jv);
	}
	
	@Override
	public void findpassword() {
		FindPassword fp = new FindPassword();
	}

	@Override
	public void findid() {
		
	}

	@Override
	public void login(JTextField user_id, JPasswordField user_password) {
		if(mdao.login(user_id,user_password)) {
			JOptionPane.showMessageDialog(null,"로그인 성공");
			loginview.setVisible(false);
			MainLobby ml = new MainLobby(user_id.getText());
			ml.setVisible(true);
//			try {
//				userSocket = new Socket("localhost",SguoConst.UPORT);
//			} catch (UnknownHostException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}else {
			JOptionPane.showMessageDialog(null,"로그인 실패");
		}
	}

}
