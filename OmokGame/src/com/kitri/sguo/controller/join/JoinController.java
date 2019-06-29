package com.kitri.sguo.controller.join;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.kitri.sguo.model.join.JoinCommandImpl;
import com.kitri.sguo.model.login.MemberDTO;
import com.kitri.sguo.view.login.JoinView;

public class JoinController implements ActionListener{

	private JTextField idtxt;
	private JTextField passwordtxt;
	private JTextField passwordchktxt;
	private JComboBox comboString;
	private JTextField anstxtfield;
	private JTextArea textArea;
	private int frameclosenum;
	private JoinView joinview;
	
	public JoinController(JTextField idtxt,JTextField passwordtxt, JTextField passwordchktxt, JComboBox comboString, JTextField anstxtfield, JTextArea textArea, int frameclosenum, JoinView joinview) {
		this.idtxt = idtxt;
		this.passwordtxt = passwordtxt;
		this.passwordchktxt = passwordchktxt;
		this.comboString = comboString;
		this.anstxtfield = anstxtfield;
		this.textArea = textArea;
		this.joinview = joinview; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JoinCommandImpl jci = new JoinCommandImpl();
		String cmd = e.getActionCommand();
		if(cmd.equals("중복확인")) {
			jci.idcheck(idtxt);
		}else if(cmd.equals("가입")) {
			if(passwordtxt.getText().equals(passwordchktxt.getText())) {
				MemberDTO mdto = new MemberDTO(idtxt.getText(),passwordtxt.getText(),comboString.getSelectedItem().toString(), anstxtfield.getText(), textArea.getText());
				jci.userjoin(mdto);
				joinview.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null,"비밀번호를 확인해주세요.");
			}
		}
		
	}

}
