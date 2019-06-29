package com.kitri.sguo.model.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean login(JTextField user_id, JPasswordField user_password) {
		
//		String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
//		String DB_USER = "c##sguo";
//		String DB_PASSWORD = "1234";
		Connect();
		boolean result = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT USER_ID, USER_PASSWORD FROM OMOK_USER WHERE USER_ID=? and user_password=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id.getText());
			pstmt.setString(2, user_password.getText());
			rs = pstmt.executeQuery();
			result=rs.next();
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
			System.out.println(result);
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
		return result;
	}
	
	public int userjoin(MemberDTO mdto) {
		int result = 0;
		Connect();
		PreparedStatement pstmt = null;
		String query = "INSERT INTO OMOK_USER (USER_ID,USER_PASSWORD,USER_INFO_QUE,QUE_HINT,USER_INTRO)\r\n" + 
				"VALUES (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mdto.getUser_id().trim());
			pstmt.setString(2, mdto.getUser_password().trim());
			pstmt.setString(3, mdto.getUser_info_que().trim());
			pstmt.setString(4, mdto.getQue_ans().trim());
			pstmt.setString(5, mdto.getUser_intro().trim());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
