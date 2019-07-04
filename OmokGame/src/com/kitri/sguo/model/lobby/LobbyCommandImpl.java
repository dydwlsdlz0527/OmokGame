package com.kitri.sguo.model.lobby;

import javax.swing.JPanel;

import com.kitri.sguo.model.login.MemberDAO;
import com.kitri.sguo.model.login.MemberDTO;
import com.kitri.sguo.view.lobby.MainLobbyView;
import com.kitri.sguo.view.lobby.MakeRoomF;

public class LobbyCommandImpl implements LobbyCommand{
	
	JPanel jp;
	MemberDAO mdao = new MemberDAO();
	MainLobbyView mainlobbyview;
	String userid;
	JPanel gameRooms;

	public LobbyCommandImpl(String userid, MainLobbyView mainlobbyview, JPanel jp) {
		// TODO Auto-generated constructor stub
		this.jp = jp;
		this.mainlobbyview = mainlobbyview;
		this.userid = userid;
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
	
	@Override
	public void MakeGameRoom(String userid, MainLobbyView mainlobbyview) {
		new MakeRoomF(userid, mainlobbyview);
	}

}
