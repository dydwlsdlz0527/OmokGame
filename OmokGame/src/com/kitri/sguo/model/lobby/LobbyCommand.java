package com.kitri.sguo.model.lobby;

import com.kitri.sguo.model.login.MemberDAO;

public interface LobbyCommand {
	
	//ȸ�� ���� ��Ÿ����
	public abstract MemberDAO getUserInfo(String memberid);

}
