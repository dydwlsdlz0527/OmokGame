package com.kitri.sguo.model.lobby;

import com.kitri.sguo.model.login.MemberDTO;

public interface LobbyCommand {
	//ȸ�� ���� ��Ÿ����
	//public abstract MemberDAO getUserInfo(String memberid);
	
	//���α׷� ����
	public abstract void exit();

	//ȸ��Ż��
	public abstract void DeleteUser(String userid);
	
	//ȸ���� ��� ���� ��������
	public abstract MemberDTO getUserInfo(String userid);
}
