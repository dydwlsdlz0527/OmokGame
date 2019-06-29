package com.kitri.sguo.model.login;

public class MemberDTO {
	
	private String user_id;
	private String user_password;
	private String user_info_que;
	private String que_ans;
	private String user_intro;
	private byte[] user_image;
	private int total_score;
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_password() {
		return user_password;
	}
	
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	public String getUser_info_que() {
		return user_info_que;
	}
	
	public void setUser_info_que(String user_info_que) {
		this.user_info_que = user_info_que;
	}
	
	public String getQue_ans() {
		return que_ans;
	}
	
	public void setQue_ans(String que_ans) {
		this.que_ans = que_ans;
	}
	
	public String getUser_intro() {
		return user_intro;
	}
	
	public void setUser_intro(String user_intro) {
		this.user_intro = user_intro;
	}
	
	public byte[] getUser_image() {
		return user_image;
	}
	
	public void setUser_image(byte[] user_image) {
		this.user_image = user_image;
	}
	
	public int getTotal_score() {
		return total_score;
	}
	
	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}
	
	public MemberDTO(String user_id, String user_password, String user_info_que, String que_ans, String user_intro,
			byte[] user_image, int total_score) {
		super();
		this.user_id = user_id;
		this.user_password = user_password;
		this.user_info_que = user_info_que;
		this.que_ans = que_ans;
		this.user_intro = user_intro;
		this.user_image = user_image;
		this.total_score = total_score;
	}
	
	public MemberDTO(String user_id, String user_password, String user_info_que, String que_ans, String user_intro) {
		super();
		this.user_id = user_id;
		this.user_password = user_password;
		this.user_info_que = user_info_que;
		this.que_ans = que_ans;
		this.user_intro = user_intro;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
