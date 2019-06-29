package com.kitri.sguo.model.join;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.kitri.sguo.model.login.MemberDAO;
import com.kitri.sguo.model.login.MemberDTO;

public class JoinCommandImpl implements JoinCommand{
	
	MemberDAO mdao = new MemberDAO();

	@Override
	public void userjoin(MemberDTO mdto) {
		if(mdao.userjoin(mdto)>0) {
			JOptionPane.showMessageDialog(null,"가입을 축하합니다.");
		}else {
			JOptionPane.showMessageDialog(null,"작성 내용을 다시 확인해주세요.");
		}
	}

	@Override
	public void joincancel() {
		
	}

	@Override
	public void idcheck(JTextField user_id) {
		if(mdao.idcheck(user_id)) {
			JOptionPane.showMessageDialog(null,"존재하는 아이디입니다.");
			user_id.setText("");
		}else {
			JOptionPane.showMessageDialog(null,"가능한 아이디입니다.");
		}
	}

}
