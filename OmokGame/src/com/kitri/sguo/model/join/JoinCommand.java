package com.kitri.sguo.model.join;

import javax.swing.JTextField;

import com.kitri.sguo.model.login.MemberDTO;

public interface JoinCommand {
	
	//회원가입
	public abstract void userjoin(MemberDTO mdto);
	
	//가입 취소
	public abstract void joincancel();
	
	//아이디 중복 확인
	public abstract void idcheck(JTextField idtxt);
}
