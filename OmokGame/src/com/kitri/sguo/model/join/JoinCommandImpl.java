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
			JOptionPane.showMessageDialog(null,"������ �����մϴ�.");
		}else {
			JOptionPane.showMessageDialog(null,"�ۼ� ������ �ٽ� Ȯ�����ּ���.");
		}
	}

	@Override
	public void joincancel() {
		
	}

	@Override
	public void idcheck(JTextField user_id) {
		if(mdao.idcheck(user_id)) {
			JOptionPane.showMessageDialog(null,"�����ϴ� ���̵��Դϴ�.");
			user_id.setText("");
		}else {
			JOptionPane.showMessageDialog(null,"������ ���̵��Դϴ�.");
		}
	}

}
