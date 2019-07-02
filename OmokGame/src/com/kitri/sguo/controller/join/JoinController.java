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
	private JTextField phonenumtxt;
	private JComboBox comboString;
	private JTextField anstxtfield;
	private JTextArea textArea;
	private JoinView joinview;
	private ImageP imagep;
	private String filepath = null;
	private File f;
	private static boolean idcheck = false;
	
	public JoinController(JTextField idtxt, JPasswordField passwordtxt, JPasswordField passwordchktxt, JTextField phonenumtxt, JComboBox comboString, JTextField anstxtfield, JTextArea textArea, JoinView joinview, ImageP imagep) {
		this.idtxt = idtxt;
		this.passwordtxt = passwordtxt;
		this.passwordchktxt = passwordchktxt;
		this.phonenumtxt = phonenumtxt;
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
			idcheck = true;
		}else if(cmd.equals("가입")) {
			if(!idcheck) {
				JOptionPane.showMessageDialog(null,"아이디 중복 확인을 해주세요.");
			}else {
				if(passwordtxt.getText().equals(passwordchktxt.getText())&&idtxt.getText().trim().length()>0) {
					MemberDTO mdto = new MemberDTO(idtxt.getText(),passwordtxt.getText(),phonenumtxt.getText(),comboString.getSelectedItem().toString(), anstxtfield.getText(), textArea.getText(), "");
					jci.userjoin(mdto, filepath);
					joinview.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null,"작성 내용을 다시 확인해주세요.");
				}
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
		}else if(cmd.equals("취소")) {
			jci.exit(joinview);
		}
		
	}

}
