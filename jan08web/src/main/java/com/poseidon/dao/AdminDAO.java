package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.dto.MemberDTO;

public class AdminDAO extends AbstractDAO {

	public List<MemberDTO> list(int grade) {
		List<MemberDTO> mem = new ArrayList<MemberDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT mno, mid, mname, mdate, mgrade FROM member WHERE mgrade=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, grade);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO e = new MemberDTO();
				e.setMno(rs.getInt(1));
				e.setMid(rs.getString(2));
				e.setMname(rs.getString(3));
				e.setMdate(rs.getString(4));
				e.setMgrade(rs.getInt(5));
				mem.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		
		return mem;
	}

	
	public List<MemberDTO> list() {
		List<MemberDTO> mem = new ArrayList<MemberDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT mno, mid, mname, mdate, mgrade FROM member";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO e = new MemberDTO();
				e.setMno(rs.getInt(1));
				e.setMid(rs.getString(2));
				e.setMname(rs.getString(3));
				e.setMdate(rs.getString(4));
				e.setMgrade(rs.getInt(5));
				mem.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		
		return mem;
	}
	
	
	public int changeGrade(MemberDTO dto) {
		int result = 0;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET mgrade=? WHERE mno=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getMgrade());
			pstmt.setInt(2, dto.getMno());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
