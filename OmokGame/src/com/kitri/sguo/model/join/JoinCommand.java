package com.kitri.sguo.model.join;

import javax.swing.JTextField;

import com.kitri.sguo.model.login.MemberDTO;

public interface JoinCommand {
	
	//ȸ������
	public abstract void userjoin(MemberDTO mdto);
	
	//���� ���
	public abstract void joincancel();
	
	//���̵� �ߺ� Ȯ��
	public abstract void idcheck(JTextField idtxt);
}
