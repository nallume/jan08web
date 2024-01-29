package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.dto.BoardDTO;
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


	public List<BoardDTO> boardList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select `b`.`board_no` AS `board_no`,`b`.`board_title` AS `board_title`,`m`.`mname` AS `mname`, "
				+ "if(date_format(current_timestamp(),'%Y-%m-%d') = date_format(`b`.`board_date`,'%Y-%m-%d'),date_format(`b`.`board_date`,'%h:%i'),date_format(`b`.`board_date`,'%m-%d')) AS `board_date`, "
				+ "(select count(0) from `visitcount` where `visitcount`.`board_no` = `b`.`board_no`) AS `board_count`, "
				+ "(select count(0) from `comment` where `comment`.`board_no` = `b`.`board_no` and `comment`.`cdel` = '1') AS `comment`, "
				+ "b.board_ip AS board_ip, b.board_del AS board_del "
				+ "from (`board` `b` join `member` `m` on(`b`.`mno` = `m`.`mno`)) order by `b`.`board_no` desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("mname"));
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("board_count"));
				e.setComment(rs.getInt("comment"));
				e.setIp(rs.getString("board_ip"));
				e.setDel(rs.getInt("board_del"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
				
		return list;
	}
	
	
	
	
	
	
}
