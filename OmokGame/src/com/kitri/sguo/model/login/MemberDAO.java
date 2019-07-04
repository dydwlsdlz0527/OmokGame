package com.kitri.sguo.model.login;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kitri.sguo.security.SHA256Util;
import com.kitri.sguo.view.login.DialogPhoneNum;
import com.kitri.sguo.view.login.JoinView;

public class MemberDAO {

	String DB_URL;
	String DB_USER;
	String DB_PASSWORD;
	Connection conn;

	public void Connect() {
		DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		DB_USER = "c##sguo";
		DB_PASSWORD = "1234";
		conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean login(JTextField user_id, JPasswordField user_password) {

//		String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
//		String DB_USER = "c##sguo";
//		String DB_PASSWORD = "1234";
		boolean result = true;
		String salt = getSaltById(user_id.getText());
		String password = user_password.getText();
		if (salt == null) {
			return false;
		}
		password = SHA256Util.getEncrypt(password, salt);
		Connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT USER_ID, USER_PASSWORD FROM OMOK_USER WHERE USER_ID=? and user_password=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id.getText());
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean idcheck(JTextField user_id) {
		Connect();
		boolean result = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT USER_ID FROM OMOK_USER WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id.getText());
			rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int userjoin(MemberDTO mdto, String filepath) {
		int result = 0;
		String salt = SHA256Util.generateSalt();
		mdto.setSalt(salt);
		String password = mdto.getUser_password().trim();
		password = SHA256Util.getEncrypt(password, salt);
		Connect();
		PreparedStatement pstmt = null;
		FileInputStream fin = null;
		try {
			if (filepath != null) {
				fin = new FileInputStream(new File(filepath));
			} else {
				fin = new FileInputStream(new File("image/defaultimage.jpg"));
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		String query = "INSERT INTO OMOK_USER (USER_ID,USER_PASSWORD,USER_PHONENUM,USER_INFO_QUE,QUE_HINT,USER_INTRO, USER_IMAGE,SALT)\r\n"
				+ "VALUES (?,?,?,?,?,?,?,?)";
		String query2 = "INSERT INTO USER_RECODE(USER_ID) VALUES (?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mdto.getUser_id().trim());
			pstmt.setString(2, password);
			pstmt.setString(3,mdto.getUser_phonenum().trim());
			pstmt.setString(4, mdto.getUser_info_que().trim());
			pstmt.setString(5, mdto.getQue_ans().trim());
			pstmt.setString(6, mdto.getUser_intro().trim());
			try {
				pstmt.setBinaryStream(7, fin, fin.available());
			} catch (IOException e) {
				e.printStackTrace();
			}
			pstmt.setString(8, salt);
			result = pstmt.executeUpdate();
			pstmt = conn.prepareStatement(query2);
			pstmt.setString(1, mdto.getUser_id().trim());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			new JoinView();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public String getSaltById(String userid) {
		String salt = null;
		Connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT SALT FROM OMOK_USER WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				salt = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salt;
	}

	public List<Object> getUserInfo(String memberid) {
		List<Object> list = new ArrayList<>();
		Connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT R.USER_ID, R.VICTORY, R.DRAW, R.DEFEAT, O.USER_IMAGE\r\n"
				+ "FROM OMOK_USER O, USER_RECODE R\r\n" + "WHERE O.USER_ID = R.USER_ID AND O.USER_ID = ?";
		InputStream in = null;
		BufferedImage bi = null;
		int len = 0;
		byte[] buf = new byte[1024];
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list.add(rs.getString(1));
				list.add(rs.getInt(2));
				list.add(rs.getInt(3));
				list.add(rs.getInt(4));
				in = rs.getBinaryStream(5);
				try {
					bi = ImageIO.read(in);
					list.add(bi);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Object> FindQue(String userphonenum){
		List<Object> list = new ArrayList<>();
		Connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT  USER_ID, USER_INFO_QUE, QUE_HINT\r\n" + 
				"FROM OMOK_USER\r\n" + 
				"WHERE USER_PHONENUM = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userphonenum.trim());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"핸드폰 번호가 틀렸습니다.");
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public void ModifyPW(String idstr, String pwstr) {
		String salt = SHA256Util.generateSalt();
		String password =  SHA256Util.getEncrypt(pwstr, salt);
		Connect();
		PreparedStatement pstmt = null;
		String query = "UPDATE OMOK_USER SET USER_PASSWORD=?, SALT=? WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, password);
			pstmt.setString(2, salt);
			pstmt.setString(3, idstr);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	public void deleteUser(String userid) {
		Connect();
		PreparedStatement pstmt = null;
		System.out.println(userid);
		String query = "DELETE OMOK_USER WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid.trim());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public MemberDTO getUserModify(String userid) {
		
		return null;
	}
	
	public String getUserRoomInfo(String userid){
		String userrankname = null;
		Connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT M2.M2M\r\n" + 
				"FROM\r\n" + 
				"(SELECT USER_ID M1ID, RATING M1R\r\n" + 
				"FROM OMOK_USER, USER_RATING\r\n" + 
				"WHERE USER_ID = ? AND TOTAL_SCORE BETWEEN LOSCORE AND HISCORE) M1,\r\n" + 
				"(SELECT USER_RATING.RATING M2R, RATING_INFO.RATINGNAME M2M\r\n" + 
				"FROM USER_RATING, RATING_INFO\r\n" + 
				"WHERE USER_RATING.RATING = RATING_INFO.RATING) M2\r\n" + 
				"WHERE M1.M1R = M2.M2R";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,userid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				userrankname = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userrankname;
	}

}
