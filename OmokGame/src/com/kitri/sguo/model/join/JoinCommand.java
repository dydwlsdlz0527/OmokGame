package com.kitri.sguo.model.join;

import javax.swing.JTextField;

import com.kitri.sguo.model.login.MemberDTO;
import com.kitri.sguo.view.login.ImageP;
import com.kitri.sguo.view.login.JoinView;

public interface JoinCommand {
	
	//회원가입
	public abstract void userjoin(MemberDTO mdto, String filepath);
	
	//가입 취소
	public abstract void exit(JoinView joinview);
	
	//아이디 중복 확인
	public abstract void idcheck(JTextField idtxt);
	
	//이미지 추가
	public abstract void paintimage(String imagepath, ImageP imagep);
}
