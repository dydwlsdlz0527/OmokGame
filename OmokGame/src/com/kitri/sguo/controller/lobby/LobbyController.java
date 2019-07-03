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

//로비에서 내린 명령을 컨트롤하는 곳
public class LobbyController extends JPanel implements ActionListener{
	
	private JPanel jp;
	private String userid;
	private MainLobbyView mainlobbyview;
	
	public LobbyController(JPanel jp) {
		this.jp = jp;
	}
	
	public LobbyController(MainLobbyView mainlobbyview, JPanel jp, String userid) {
		this.jp = jp;
		this.userid = userid;
		this.mainlobbyview = mainlobbyview;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LobbyCommandImpl lci = new LobbyCommandImpl(jp);
		String cmd = e.getActionCommand();
		//프로그램 종료
		if(cmd.equals("나가기")) {
			lci.exit();
		}else if(cmd.equals("1:1 쪽지")){
			
		}else if(cmd.equals("방만들기")) {
			new MakeRoomF(userid);
		}else if(cmd.equals("혼자하기")) {
			new GameView();
		}else if(cmd.equals("게임정보")) {
			
		}else if(cmd.equals("탈퇴")) {
			String deluserid = JOptionPane.showInputDialog("삭제할 아이디를 입력하세요.");
			if(deluserid.equals(userid)) {
				lci.DeleteUser(userid);
				mainlobbyview.dispose();
				new LoginView();
			}
		}else if(cmd.equals("수정")) {
			//나중에
			//MemberDTO mdto = lci.ModifyUserInfo(userid);
			//new ModifyUserInfoView();
		}
	}
	
}
