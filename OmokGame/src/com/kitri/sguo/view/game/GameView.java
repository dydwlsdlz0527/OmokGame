package com.kitri.sguo.view.game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

//오목 두는 화면
public class GameView extends JFrame implements KeyListener{
	
	private Container c;				//container 타입으로 c변수 선언
	MapSize size = new MapSize();		//MapSize 클래스 객체 생성, 셀과 사이즈의 크기가 선언된다. 30x20
	private JTextField gamechattxt;
	private Map map;
	private DrawBoard d;
	private JTextArea gamechatarea;
	private JScrollPane scrollPane;
	private JButton gameexit;
	private JButton rego;
	private JButton startgame;
	private JLabel p2shift;
	private JLabel p2id;
	private JLabel p1shift;
	private JLabel p1id;
	private JPanel p2userP;
	private JPanel p1userP;
	
	public GameView() {			//GUI 생성자 실행
		c = getContentPane();
		setBounds(200, 20, 947, 687);	//컨테이너 위치와 크기를 생성.
		c.setLayout(null);
		map = new Map(size);
		d = new DrawBoard(size, map);
		d.setPreferredSize(new Dimension(300,300));		//드로우보드 사이즈 설정.
		setContentPane(d);	//메인 프레임에 드로우보드 넣기.
		
		//이벤트처리 핸들러 생성.
		addMouseListener(new MouseEventHandler(map, size, d, this));
		
		JPanel userP = new JPanel();
		userP.setBounds(610, 10, 298, 610);
		d.add(userP);
		userP.setLayout(null);
		
		p1userP = new JPanel();
		p1userP.setBounds(12, 10, 136, 179);
		userP.add(p1userP);
		
		p2userP = new JPanel();
		p2userP.setBounds(150, 10, 136, 179);
		userP.add(p2userP);
		
		p1id = new JLabel("");
		p1id.setBounds(12, 199, 136, 22);
		userP.add(p1id);
		
		p1shift = new JLabel("");
		p1shift.setBounds(12, 227, 136, 22);
		userP.add(p1shift);
		
		p2id = new JLabel("");
		p2id.setBounds(150, 199, 136, 22);
		userP.add(p2id);
		
		p2shift = new JLabel("");
		p2shift.setBounds(150, 227, 136, 22);
		userP.add(p2shift);
		
		startgame = new JButton("\uC2DC\uC791");
		startgame.setBounds(12, 259, 77, 45);
		userP.add(startgame);
		
		rego = new JButton("\uBB34\uB974\uAE30");
		rego.setBounds(111, 259, 77, 45);
		userP.add(rego);
		
		gameexit = new JButton("\uB098\uAC00\uAE30");
		gameexit.setBounds(209, 259, 77, 45);
		userP.add(gameexit);
		
		JPanel gamechatP = new JPanel();
		gamechatP.setBounds(12, 324, 274, 276);
		userP.add(gamechatP);
		gamechatP.setLayout(new BorderLayout());
		
		gamechatarea = new JTextArea();
		gamechatarea.setRows(12);
		scrollPane = new JScrollPane(gamechatarea);
		gamechatP.add(scrollPane, BorderLayout.NORTH);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		gamechattxt = new JTextField();
		gamechatP.add(gamechattxt, BorderLayout.SOUTH);
		gamechattxt.setColumns(50);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void showPopUp(String message) {
		JOptionPane.showMessageDialog(this, message, "", JOptionPane.INFORMATION_MESSAGE);
		//게임 종료시 화면 다시 그리기.
		d.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			String msg = gamechattxt.getText();
			gamechatarea.append(msg);
			gamechattxt.setText("");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
