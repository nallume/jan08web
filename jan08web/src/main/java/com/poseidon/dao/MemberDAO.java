package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.MemberDTO;

//login, 회원가입, 회원탈퇴처리, 회원 정보보기
public class MemberDAO extends AbstractDAO{

	public MemberDTO login(MemberDTO dto) {
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) AS count, mname FROM member WHERE mid=? AND mpw=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getMpw());
			
			rs = pstmt.executeQuery();
			//rs에서 풀어준다 어차피 하나밖에 안나온다
			if (rs.next()) {
				dto.setCount(rs.getInt("count"));
				dto.setMname(rs.getString("mname"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return dto;
	}

	public MemberDTO myInfo(MemberDTO dto) {
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE mid=?";
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setMno(rs.getInt("mno"));
				dto.setMname(rs.getString("mname"));
				dto.setMpw(rs.getString("mpw"));
				dto.setMdate(rs.getString("mdate"));
				dto.setMgrade(rs.getInt("mgrade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		
		return dto;
	}

	public int changePW(MemberDTO dto) {
		int result = 0;
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET mpw=? WHERE mid =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMpw());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return result;
	}

	public int join(MemberDTO dto) {
		int result = 0;
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO member(mid, mpw, mname) VALUES (?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getMpw());
			pstmt.setString(3, dto.getMname());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		
		return result;
	}

	public int idcheck(MemberDTO dto) {
		// 1이면 중복 아이디가 있으니까 가입이 안되고, 0이면 중복 아이디가 없으니까 회원가입이 되고!
		// 오류났을때 대비, 기본값을 1로 두고 회원가입 못하게 막는 것
		int result = 1;
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM member WHERE MID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
				
		return result;
	}

	
	public List<Map<String, Object>> readData(MemberDTO dto) {
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT v.vno AS vno, b.board_no AS board_no, b.board_title AS board_title, v.vdate AS vdate "
				+ "FROM visitcount v join board b "
				+ "ON b.board_no = v.board_no "
				+ "WHERE v.mno = (SELECT mno FROM member WHERE MID = ?) ORDER BY v.vno DESC";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Map<String, Object> e = new HashMap<String, Object>();
				e.put("vno", rs.getInt("vno"));
				e.put("board_no", rs.getInt("board_no"));
				e.put("board_title", rs.getString("board_title"));
				//e.put("mno", rs.getInt("mno"));
				e.put("vdate", rs.getString("vdate"));
				data.add(e);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
						
		return data;
	}

	
}

