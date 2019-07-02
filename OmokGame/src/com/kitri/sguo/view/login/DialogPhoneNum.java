package com.kitri.sguo.view.login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.kitri.sguo.model.login.MemberDAO;

public class DialogPhoneNum extends JDialog {

	//private JDialog dialog;
	private MemberDAO mdao;
	private JTextField textField;
	private List<Object> list;
	private JButton searchbtn;

	public DialogPhoneNum(LoginView loginview) {

		setTitle("아이디/비밀번호 찾기");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC804\uD654\uBC88\uD638\uB97C \uC785\uB825\uD558\uC138\uC694.");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel.setBounds(88, 10, 220, 31);
		getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(12, 51, 207, 47);
		getContentPane().add(textField);
		textField.setColumns(10);

		searchbtn = new JButton("\uC785\uB825");
		searchbtn.setBounds(231, 51, 85, 47);
		getContentPane().add(searchbtn);

		// 336, 194
		setResizable(false);
		setSize(336, 194);
		setVisible(true);
		setLocationRelativeTo(null);

		searchbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberDAO mdao = new MemberDAO();
				list = new ArrayList<>();
				String phonestr = textField.getText();
				if (phonestr.length() > 0) {
					list = mdao.FindQue(phonestr);
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "잘못된 번호 입니다.");
					}else {
						new FindPasswordView(list);
						dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "핸드폰 번호를 입력하세요.");
				}
			}
		});
	}
}
