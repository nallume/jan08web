package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poseidon.db.DBConnection;

//부모 DAO - DBConn, close
public class AbstractDAO {

	DBConnection db = DBConnection.getInstance();
	
	void close(ResultSet rs, PreparedStatement pstmt, Connection con) {	
		//rs가 null이 아닐때 = 객체가 있을 때
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	
}
