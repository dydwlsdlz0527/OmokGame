package com.kitri.sguo.view.login;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.kitri.sguo.model.login.MemberDAO;


public class FindPasswordView extends JFrame{

	private JDialog d;
	private JTextField uans;
	private JLabel questionL;
	private JLabel title;
	private JLabel uquestion;
	private JLabel ans;
	private JLabel idL;
	private JLabel finduserid;
	private JLabel pwL;
	private JButton searchbtn;
	private JTextField modifyuserpw;
	private List<Object> list;
	private JButton modifybtn;
	
	public FindPasswordView(List<Object> list) {
//		JFrame f = new JFrame();
//		d = new JDialog(f,"아이디/비밀번호 찾기",true);
//		d.getContentPane().setLayout(null);
		this.list = list;
		setTitle("아이디/비밀번호 찾기");
		getContentPane().setLayout(null);
		questionL = new JLabel("\uC9C8\uBB38");
		questionL.setBounds(78, 56, 30, 21);
		getContentPane().add(questionL);
		
		title = new JLabel("\uC544\uC774\uB514/\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		title.setFont(new Font("굴림", Font.BOLD, 14));
		title.setBounds(115, 10, 219, 36);
		getContentPane().add(title);
		
		uquestion = new JLabel(list.get(1)+"");
		uquestion.setBounds(115, 46, 177, 36);
		getContentPane().add(uquestion);
		
		ans = new JLabel("\uB2F5");
		ans.setBounds(88, 92, 18, 35);
		getContentPane().add(ans);
		
		uans = new JTextField();
		uans.setBounds(115, 92, 177, 36);
		getContentPane().add(uans);
		uans.setColumns(10);
		
		idL = new JLabel("\uC544\uC774\uB514");
		idL.setBounds(70, 138, 45, 36);
		getContentPane().add(idL);
		
		finduserid = new JLabel(list.get(0)+"");
		finduserid.setFont(new Font("굴림", Font.BOLD, 13));
		finduserid.setBounds(117, 138, 175, 36);
		getContentPane().add(finduserid);
		finduserid.setVisible(false);
		
		pwL = new JLabel("\uBE44\uBC00\uBC88\uD638");
		pwL.setBounds(60, 184, 62, 35);
		getContentPane().add(pwL);
		
		searchbtn = new JButton("\uCC3E\uAE30");
		searchbtn.setBounds(305, 46, 77, 36);
		getContentPane().add(searchbtn);
		
		modifyuserpw = new JTextField();
		modifyuserpw.setFont(new Font("굴림", Font.BOLD, 13));
		modifyuserpw.setBounds(117, 183, 175, 36);
		getContentPane().add(modifyuserpw);
		
		modifybtn = new JButton("\uC644\uB8CC");
		modifybtn.setBounds(305, 184, 77, 36);
		getContentPane().add(modifybtn);
	
		setSize(430,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		searchbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(uans.getText().equals(list.get(2)+"")) {
					finduserid.setVisible(true);
					JOptionPane.showMessageDialog(null,"비밀번호를 새로 작성하세요.");
				}else {
					JOptionPane.showMessageDialog(null,"질문에 대한 답이 아닙니다.");
				}
			}
		});
		modifybtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String pwstr = modifyuserpw.getText();
				MemberDAO mdao = new MemberDAO();
				mdao.ModifyPW(finduserid.getText(),pwstr);
				dispose();
				JOptionPane.showMessageDialog(null,"비밀번호가 수정되었습니다.");
			}
			
		});
		
	}
}
