package com.kitri.sguo.model.lobby;

import javax.swing.JPanel;

import com.kitri.sguo.model.login.MemberDTO;
import com.kitri.sguo.view.lobby.MainLobbyView;

public interface LobbyCommand {
	//ȸ�� ���� ��Ÿ����
	//public abstract MemberDAO getUserInfo(String memberid);
	
	//���α׷� ����
	public abstract void exit();

	//ȸ��Ż��
	public abstract void DeleteUser(String userid);
	
	//ȸ���� ��� ���� ��������
	public abstract MemberDTO ModifyUserInfo(String userid);
	
	//���ӹ� �����
	public abstract void MakeGameRoom(String userid, MainLobbyView mainlobbyview);
}
