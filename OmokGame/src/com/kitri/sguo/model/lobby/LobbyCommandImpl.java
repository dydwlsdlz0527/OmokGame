package com.kitri.sguo.model.lobby;

import javax.swing.JPanel;

import com.kitri.sguo.model.login.MemberDAO;
import com.kitri.sguo.model.login.MemberDTO;

public class LobbyCommandImpl implements LobbyCommand{
	
	private JPanel jp;
	private MemberDAO mdao = new MemberDAO();

	public LobbyCommandImpl(JPanel jp) {
		// TODO Auto-generated constructor stub
		this.jp = jp;
	}

	@Override
	public void exit() {
		System.exit(0);
	}

	@Override
	public void DeleteUser(String userid) {
		mdao.deleteUser(userid);
	}

	//회원정보수정할 때 필요한 값.
	@Override
	public MemberDTO ModifyUserInfo(String userid) {
		MemberDTO mdto = mdao.getUserModify(userid);
		return mdto;
	}

}
