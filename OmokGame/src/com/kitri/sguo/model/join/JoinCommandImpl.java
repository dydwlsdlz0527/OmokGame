package com.kitri.sguo.model.join;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.kitri.sguo.model.login.MemberDAO;
import com.kitri.sguo.model.login.MemberDTO;
import com.kitri.sguo.view.login.ImageP;
import com.kitri.sguo.view.login.JoinView;

public class JoinCommandImpl implements JoinCommand {

	MemberDAO mdao = new MemberDAO();

	@Override
	public void userjoin(MemberDTO mdto, String filepath) {
		if (mdao.userjoin(mdto, filepath) > 0) {
			JOptionPane.showMessageDialog(null, "������ �����մϴ�.");
		} else {
			JOptionPane.showMessageDialog(null, "�ۼ� ������ �ٽ� Ȯ�����ּ���.");
		}
	}

	@Override
	public void exit(JoinView joinview) {
			joinview.dispose();
	}

	@Override
	public void idcheck(JTextField user_id) {
		if (user_id.getText().trim().length()==0) {
			JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���!");
		} else {
			if (mdao.idcheck(user_id)) {
				JOptionPane.showMessageDialog(null, "�����ϴ� ���̵��Դϴ�.");
				user_id.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "������ ���̵��Դϴ�.");
			}
		}
	}

	@Override
	public void paintimage(String imagepath, ImageP imagep) {
		imagep.setImage(imagepath);
	}

}
