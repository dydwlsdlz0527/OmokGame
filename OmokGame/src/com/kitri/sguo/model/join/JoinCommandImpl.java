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
			JOptionPane.showMessageDialog(null, "가입을 축하합니다.");
		} else {
			JOptionPane.showMessageDialog(null, "작성 내용을 다시 확인해주세요.");
		}
	}

	@Override
	public void exit(JoinView joinview) {
			joinview.dispose();
	}

	@Override
	public void idcheck(JTextField user_id) {
		if (user_id.getText().trim().length()==0) {
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요!");
		} else {
			if (mdao.idcheck(user_id)) {
				JOptionPane.showMessageDialog(null, "존재하는 아이디입니다.");
				user_id.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "가능한 아이디입니다.");
			}
		}
	}

	@Override
	public void paintimage(String imagepath, ImageP imagep) {
		imagep.setImage(imagepath);
	}

}
