package com.kitri.sguo.model.lobby;

import javax.swing.JPanel;

import com.kitri.sguo.model.login.MemberDTO;
import com.kitri.sguo.view.lobby.MainLobbyView;

public interface LobbyCommand {
	//회원 정보 나타내기
	//public abstract MemberDAO getUserInfo(String memberid);
	
	//프로그램 종료
	public abstract void exit();

	//회원탈퇴
	public abstract void DeleteUser(String userid);
	
	//회원의 모든 정보 가져오기
	public abstract MemberDTO ModifyUserInfo(String userid);
	
	//게임방 만들기
	public abstract void MakeGameRoom(String userid, MainLobbyView mainlobbyview);
}
