package com.kitri.sguo.controller.lobby;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kitri.sguo.model.lobby.LobbyCommandImpl;
import com.kitri.sguo.model.login.MemberDTO;
import com.kitri.sguo.net.client.ClientSocket;
import com.kitri.sguo.net.constdata.SguoConst;
import com.kitri.sguo.view.game.GameView;
import com.kitri.sguo.view.lobby.MainLobbyView;
import com.kitri.sguo.view.lobby.MakeRoomF;
import com.kitri.sguo.view.lobby.ModifyUserInfoView;
import com.kitri.sguo.view.login.LoginView;

//로비에서 내린 명령을 컨트롤하는 곳
public class LobbyController extends JPanel implements ActionListener{
	
	JPanel jp;
	String userid;
	MainLobbyView mainlobbyview;
	JPanel gameRooms;
	Socket socket;
	
	public LobbyController(MainLobbyView mainlobbyview, JPanel jp, String userid, JPanel gameRooms) {
		this.jp = jp;
		this.userid = userid;
		this.mainlobbyview = mainlobbyview;
		this.gameRooms = gameRooms;
		socket = ClientSocket.getSocket();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LobbyCommandImpl lci = new LobbyCommandImpl(userid, mainlobbyview, jp);
		String cmd = e.getActionCommand();
		//프로그램 종료
		if(cmd.equals("나가기")) {
			System.out.println("유저 나감");
			send(SguoConst.EXITLOBBY+"||"+userid);
			lci.exit();
		}else if(cmd.equals("1:1 쪽지")){
			
		}else if(cmd.equals("방만들기")) {
			lci.MakeGameRoom(userid, mainlobbyview);
			mainlobbyview.repaint();
		}else if(cmd.equals("혼자하기")) {
			//new GameView();
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
		}else if(cmd.equals("입장하기")) {
			
		}
	}
	
	void send(String data) {
		Thread thread = new Thread() {
			public void run() {
				try {
					System.out.println("서버에 보내는 데이터 : " + data);
					byte[] byteArr = data.getBytes("UTF-8");
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(byteArr);
					outputStream.flush();
				} catch (Exception e) {
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
}
