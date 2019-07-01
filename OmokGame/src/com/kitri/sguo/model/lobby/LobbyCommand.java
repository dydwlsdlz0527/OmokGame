package com.kitri.sguo.model.lobby;

import com.kitri.sguo.model.login.MemberDAO;

public interface LobbyCommand {
	
	//회원 정보 나타내기
	public abstract MemberDAO getUserInfo(String memberid);

}
