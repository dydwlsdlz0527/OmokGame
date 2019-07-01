package com.kitri.sguo.controller.join;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.kitri.sguo.model.join.JoinCommandImpl;
import com.kitri.sguo.model.login.MemberDTO;
import com.kitri.sguo.view.login.ImageP;
import com.kitri.sguo.view.login.JoinView;

public class JoinController implements ActionListener{

	private JTextField idtxt;
	private JPasswordField passwordtxt;
	private JPasswordField passwordchktxt;
	private JComboBox comboString;
	private JTextField anstxtfield;
	private JTextArea textArea;
	private JoinView joinview;
	private ImageP imagep;
	private String filepath = null;
	private File f;
	
	public JoinController(JTextField idtxt,JPasswordField passwordtxt, JPasswordField passwordchktxt, JComboBox comboString, JTextField anstxtfield, JTextArea textArea, JoinView joinview, ImageP imagep) {
		this.idtxt = idtxt;
		this.passwordtxt = passwordtxt;
		this.passwordchktxt = passwordchktxt;
		this.comboString = comboString;
		this.anstxtfield = anstxtfield;
		this.textArea = textArea;
		this.joinview = joinview;
		this.imagep = imagep;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JoinCommandImpl jci = new JoinCommandImpl();
		String cmd = e.getActionCommand();
		if(cmd.equals("중복확인")) {
			jci.idcheck(idtxt);
		}else if(cmd.equals("가입")) {
			if(passwordtxt.getText().equals(passwordchktxt.getText())) {
				MemberDTO mdto = new MemberDTO(idtxt.getText(),passwordtxt.getText(),comboString.getSelectedItem().toString(), anstxtfield.getText(), textArea.getText(), "");
				jci.userjoin(mdto, filepath);
				joinview.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null,"비밀번호를 확인해주세요.");
			}
		}else if(cmd.equals("이미지추가")){
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("이미지 불러오기");
			//jpg 파일만 선택
			fc.setFileFilter(new FileNameExtensionFilter("JPG File", "jpg"));
			//다중 선택 false
			fc.setMultiSelectionEnabled(false);
			//joinview에 띄우기
			int i = fc.showOpenDialog(joinview);
			//파일을 선택했을 때
			if(i==JFileChooser.APPROVE_OPTION) {
				f = fc.getSelectedFile();
				filepath = f.getPath();
				//파일의 경로와 해당 패널 보내기
				jci.paintimage(filepath, imagep);
			}
		}else if(cmd.equals("이미지삭제")) {
			
		}
		
	}

}
