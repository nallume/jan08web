package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.db.DBConnection;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

public class BoardDAO extends AbstractDAO {

	// 게시판 첫 화면
	// DB 각각의 정보가 나열될거니까 List로 리턴 타입은 BoardDTO
	//(불러온걸 dto의 getter와 setter 써서 담으려구)
	public List<BoardDTO> boardList(int page) {
		List<BoardDTO> list = null;
		// db정보
		//DBConnection db = DBConnection.getInstance(); // new 생성자 못쓰는 것 체크
		// conn 객체
		Connection con = null;
		// pstmt
		PreparedStatement pstmt = null;
		// Statement가 sql인젝션 공격에 취약해서 나온 것

		// rs
		ResultSet rs = null;
		// sql
		String sql = "SELECT * FROM boardview LIMIT ?, 10";
		con = db.getConnection();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (page - 1) * 10); // page는 1부터 시작. 글은 0번째 부터 시작.
			//1페이지에 limit 0, 10 -> 2페이지에 10, 10 이렇게 되기 위함
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardDTO>();
			// while문 안쪽만 아니면 상관 없는데, 미리 null로 선언하고 하나하나 오류 잡으면서 가는걸 추천
			// 오류난 구간을 쉽게 찾기 위한

			while (rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt(1));
				e.setTitle(rs.getString(2));
				e.setWrite(rs.getString(3));
				e.setDate(rs.getString(4));
				e.setCount(rs.getInt(5));
				e.setComment(rs.getInt(6));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pstmt,con);
		}

		return list;
	}


	public BoardDTO detail(int no) {
		BoardDTO dto = new BoardDTO();
		
		//Connection con = DBConnection.getInstance().getConnection();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	    String sql = "SELECT b.board_no, b.board_title, b.board_content, m.mname as board_write, m.mid, b.board_ip, "
	              + "b.board_date, (SELECT COUNT(*) FROM visitcount WHERE board_no = b.board_no) AS board_count "
	              + "FROM board b JOIN member m ON b.mno=m.mno "
	              + "WHERE b.board_no=?";
	    
	    //countUp(no); //조회수 올리기
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("board_no"));
				dto.setTitle(rs.getString("board_title"));
				dto.setContent(rs.getString("board_content"));
				dto.setWrite(rs.getString("board_write"));
				dto.setDate(rs.getString("board_date"));
				dto.setCount(rs.getInt("board_count"));
				dto.setMid(rs.getString("mid"));
				dto.setIp(Util.ipMasking(rs.getString("board_ip")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return dto;
	}

	public int write(BoardDTO dto) {
		int result = 0;
		//초기값 0, 문제가 발생하면 0이 그대로 날라오겠지
		
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		//rs는 없어도 됨
		String sql = "INSERT INTO board (board_title, board_content, mno, board_ip) "
				+ "VALUES(?,?,(SELECT mno FROM member WHERE mid=?), ?)"; //수정 완
		
		try {
			pstmt = con.prepareStatement(sql);
			//? 값 저장
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getMid()); //수정 완
			pstmt.setString(4, dto.getIp()); //아이피 추가
			result = pstmt.executeUpdate(); //영향받은 행을 result에 저장
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}

	public int delete(BoardDTO dto) {
		int result = 0;
		
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_del='0' WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}

	public int update(BoardDTO dto) {
		int result = 0;
		
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE board SET board_title=?, board_content=? "
				+ "WHERE board_no =? AND mno=(SELECT mno FROM member WHERE mid=?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			//? 값 저장
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNo());
			pstmt.setString(4, dto.getMid());
			result = pstmt.executeUpdate(); //영향받은 행을 result에 저장
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}


	public int totalCount() {
		int result = 0;
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM boardview";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return result;
	}
	
	public void countUp(int no, String mid) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//먼저 질의 : 해당 글을 현재 로그인한 사람이 읽은 기록이 visitcount 테이블에 있느냐?
		String sql = "SELECT count(*) FROM visitcount WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";
//		String sql = "UPDATE board SET board_count = board_count + 1 WHERE board_no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int result = rs.getInt(1); //select문에 따라 조회된 결과 테이블 중 1열의 값을 저장
				if(result == 0) {
					realCountUp(no,mid);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
	}


	private void realCountUp(int no, String mid) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO visitcount(board_no, mno) VALUES(?, (SELECT mno FROM member WHERE mid=?))";
		//조회 결과가 0인 경우 하나 추가하는 거니까 update가 아니고 insert
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
	}


	public List<CommentDTO> commentList(int no) {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM commentview WHERE board_no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setCno(rs.getInt("cno"));
				dto.setBno(rs.getInt("board_no"));
				dto.setMno(rs.getInt("mno")); // 이제 뷰 만들어서 mname, mid가져오게 해야 함...
				dto.setMid(rs.getString("mid"));
				dto.setMname(rs.getString("mname"));
				dto.setCcomment(rs.getString("ccomment"));
				dto.setCdate(rs.getString("cdate"));
				dto.setClike(rs.getInt("clike"));
				dto.setCip(Util.ipMasking(rs.getString("cip")));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}


	
}
