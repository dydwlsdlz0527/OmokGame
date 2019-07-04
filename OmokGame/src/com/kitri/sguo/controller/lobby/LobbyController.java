package com.kitri.sguo.controller.lobby;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kitri.sguo.model.lobby.LobbyCommandImpl;
import com.kitri.sguo.model.login.MemberDTO;
import com.kitri.sguo.view.game.GameView;
import com.kitri.sguo.view.lobby.MainLobbyView;
import com.kitri.sguo.view.lobby.MakeRoomF;
import com.kitri.sguo.view.lobby.ModifyUserInfoView;
import com.kitri.sguo.view.login.LoginView;

//�κ񿡼� ���� ����� ��Ʈ���ϴ� ��
public class LobbyController extends JPanel implements ActionListener{
	
	JPanel jp;
	String userid;
	MainLobbyView mainlobbyview;
	JPanel gameRooms;
	
	public LobbyController(MainLobbyView mainlobbyview, JPanel jp, String userid, JPanel gameRooms) {
		this.jp = jp;
		this.userid = userid;
		this.mainlobbyview = mainlobbyview;
		this.gameRooms = gameRooms;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LobbyCommandImpl lci = new LobbyCommandImpl(userid, mainlobbyview, jp);
		String cmd = e.getActionCommand();
		//���α׷� ����
		if(cmd.equals("������")) {
			lci.exit();
		}else if(cmd.equals("1:1 ����")){
			
		}else if(cmd.equals("�游���")) {
			lci.MakeGameRoom(userid, mainlobbyview);
			mainlobbyview.repaint();
		}else if(cmd.equals("ȥ���ϱ�")) {
			//new GameView();
		}else if(cmd.equals("��������")) {
			
		}else if(cmd.equals("Ż��")) {
			String deluserid = JOptionPane.showInputDialog("������ ���̵� �Է��ϼ���.");
			if(deluserid.equals(userid)) {
				lci.DeleteUser(userid);
				mainlobbyview.dispose();
				new LoginView();
			}
		}else if(cmd.equals("����")) {
			//���߿�
			//MemberDTO mdto = lci.ModifyUserInfo(userid);
			//new ModifyUserInfoView();
		}else if(cmd.equals("�����ϱ�")) {
			
		}
	}
	
}
