package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import com.poseidon.dto.CoffeeDTO;
import com.poseidon.team.TeamC;

public class CoffeeDAO extends AbstractDAO {
	
	public List<String> ClassMate() {
		List<String> list = new ArrayList<String>();
		
		TeamC t = new TeamC();
		
		list = t.getMembers();		
		Collections.sort(list);		
		list.add("윤승");
		
		
		return list;
	}
	
	
	
	
	public int menu(CoffeeDTO dto) {
		int result = 0;
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE coffee SET menu=?, ice=? WHERE clno=(SELECT clno FROM coffee WHERE clname=?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getDrink());
			pstmt.setInt(2, dto.getIce());
			pstmt.setString(3, dto.getClName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return result;
	}




	public Map<String, Integer> total() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM coffeeview";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				map.put("coffeehot", rs.getInt(1));
				map.put("coffeeice", rs.getInt(2));
				map.put("teahot", rs.getInt(3));
				map.put("teaice", rs.getInt(4));
				map.put("none", rs.getInt(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}		
		
		return map;
	}




	public int nameCheck(CoffeeDTO dto) {
		int result = 0;
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM coffee WHERE clname=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getClName());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return result;
	}

}
