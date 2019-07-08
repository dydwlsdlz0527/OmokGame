package com.kitri.sguo.view.game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.kitri.sguo.model.game.GameUser;
import com.kitri.sguo.view.lobby.LobbyUserImageP;

//���� �δ� ȭ��
public class GameView extends JFrame implements KeyListener {

	Container c; // container Ÿ������ c���� ����
	MapSize size = new MapSize(); // MapSize Ŭ���� ��ü ����, ���� �������� ũ�Ⱑ ����ȴ�. 30x20
	JTextField gamechattxt;
	Map map;
	DrawBoard d;
	JTextArea gamechatarea;
	JScrollPane scrollPane;
	JButton gameexit;
	JButton rego;
	JButton startgame;
	JLabel p2shift;
	JLabel p2id;
	JLabel p1shift;
	JLabel p1id;
	JPanel p2userP;
	JPanel p1userP;
	GameUser user;
	BufferedImage bi;
	

	// ���ӹ� ��ȣ
	int roomnum;
	// ���ӹ濡 ������ ����
	List<GameUser> userList;
	// ���ӹ��� ����
	GameUser roomOwner;
	// ���ӹ� �̸�
	String roomName;
	FileOutputStream fos;

	public GameView() { // GUI ������ ����
		c = getContentPane();
		setBounds(200, 20, 947, 687); // �����̳� ��ġ�� ũ�⸦ ����.
		c.setLayout(null);
		map = new Map(size);
		d = new DrawBoard(size, map);
		d.setPreferredSize(new Dimension(300, 300)); // ��ο캸�� ������ ����.
		setContentPane(d); // ���� �����ӿ� ��ο캸�� �ֱ�.

		// �̺�Ʈó�� �ڵ鷯 ����.
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
		gamechatarea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		gamechatarea.setRows(12);
		scrollPane = new JScrollPane(gamechatarea);
		gamechatP.add(scrollPane, BorderLayout.NORTH);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		gamechattxt = new JTextField();
		gamechattxt.setFont(new Font("����", Font.PLAIN, 15));
		gamechatP.add(gamechattxt, BorderLayout.SOUTH);
		gamechattxt.setColumns(50);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public GameView(GameUser user) {
		this.user = user;
		this.roomnum = user.roomnum;
		userList = new ArrayList<>();
		user.enterRoom(this);
		userList.add(user);
		this.roomOwner = user;

		c = getContentPane();
		setBounds(200, 20, 947, 687); // �����̳� ��ġ�� ũ�⸦ ����.
		c.setLayout(null);
		map = new Map(size);
		d = new DrawBoard(size, map);
		d.setPreferredSize(new Dimension(300, 300)); // ��ο캸�� ������ ����.
		setContentPane(d); // ���� �����ӿ� ��ο캸�� �ֱ�.

		// �̺�Ʈó�� �ڵ鷯 ����.
		addMouseListener(new MouseEventHandler(map, size, d, this));

		JPanel userP = new JPanel();
		userP.setBounds(610, 10, 298, 610);
		d.add(userP);
		userP.setLayout(null);

		try {
			bi = ImageIO.read(new ByteArrayInputStream(userList.get(0).userimg.getBytes()));
			System.out.println(bi+"Bibibibibibibi");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p1userP = new LobbyUserImageP(bi);
		p1userP.setBounds(12, 10, 136, 179);
		userP.add(p1userP);

		p2userP = new JPanel();
		p2userP.setBounds(150, 10, 136, 179);
		userP.add(p2userP);
		System.out.println(userList.get(0).userid);
		p1id = new JLabel("���̵� : " + userList.get(0).userid);
		p1id.setBounds(12, 199, 136, 22);
		userP.add(p1id);

		p1shift = new JLabel("�·� : " + userList.get(0).shift);
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
		gamechatarea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		gamechatarea.setRows(12);
		scrollPane = new JScrollPane(gamechatarea);
		gamechatP.add(scrollPane, BorderLayout.NORTH);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		gamechattxt = new JTextField();
		gamechattxt.setFont(new Font("����", Font.PLAIN, 15));
		gamechatP.add(gamechattxt, BorderLayout.SOUTH);
		gamechattxt.setColumns(50);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public void showPopUp(String message) {
		JOptionPane.showMessageDialog(this, message, "", JOptionPane.INFORMATION_MESSAGE);
		// ���� ����� ȭ�� �ٽ� �׸���.
		d.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String msg = gamechattxt.getText();
			gamechattxt.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void enterUser(GameUser user) {
		System.out.println(userList.size() + "����Ʈ������");
		userList = new ArrayList<>();
		user.enterRoom(this);
		userList.add(user);
		if (userList.size() == 0) {
			this.roomOwner = user;
		}
	}

	public void enterUser(List<GameUser> users) {
		for (GameUser gameUser : users) {
			gameUser.enterRoom(this);
		}
		userList.addAll(users);
	}

	// �濡�� ������
	public void exitUser(GameUser user) {
		user.exitRoom(this);
		// ���� ������ ����Ʈ���� ����.
		userList.remove(user);
		if (userList.size() < 2) {
			this.roomOwner = userList.get(0);
			return;
		}
	}

	@Override
	public int hashCode() {
		return roomnum;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		GameUser gameUser = (GameUser) obj;

		return user.userid == gameUser.userid;
	}

	public void setOwner(GameUser owner) {
		this.roomOwner = owner;
	}

	public void close() {
		for (GameUser user : userList) {
			user.exitRoom(this);
		}
		this.userList.clear();
		this.userList = null;
	}

	public void getowner() {
		System.out.println("���� : " + this.roomOwner);
	}

	public int getroomnum() {
		return this.roomnum;
	}

	public int getRoomId() {
		return roomnum;
	}

	public GameView getRoom(int roomnum) {
		if (this.roomnum == roomnum) {
			return this;
		} else {
			return null;
		}
	}

}
