package com.kitri.sguo.model.join;

import javax.swing.JTextField;

import com.kitri.sguo.model.login.MemberDTO;
import com.kitri.sguo.view.login.ImageP;

public interface JoinCommand {
	
	//ȸ������
	public abstract void userjoin(MemberDTO mdto, String filepath);
	
	//���� ���
	public abstract void joincancel();
	
	//���̵� �ߺ� Ȯ��
	public abstract void idcheck(JTextField idtxt);
	
	//�̹��� �߰�
	public abstract void paintimage(String imagepath, ImageP imagep);
}
