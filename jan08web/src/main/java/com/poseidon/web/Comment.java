package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글처리. 들어오는 값은 무조건 set
		request.setCharacterEncoding("UTF-8");
		
		//세션
		HttpSession session = request.getSession();

		// 오는 값 받기
		
		//댓글 내용
		String commentcontent = request.getParameter("commentcontent");
		//댓글 내용에서 특수기호 <,>,변경하기 & 줄바꿈 처리해주기
								
		//html에서 태그를 특수기호로 변경하기
		commentcontent = Util.removeTag(commentcontent);
		
		//엔터처리 (줄바꿈) /r /n /nr
		commentcontent = Util.addBR(commentcontent);
		
		//글 번호
		String bno = request.getParameter("bno");

		// 테스트
		// System.out.println(commentcontent + " : " + bno);

		// 로그인 검사
		if (session.getAttribute("mid") != null && commentcontent != null && bno != null) {

			// 저장하기
			CommentDTO dto = new CommentDTO();
			dto.setCcomment(commentcontent);
			dto.setBno(Util.str2Int(bno));
			dto.setMid((String) session.getAttribute("mid"));
			dto.setCip(Util.getIP(request));

			CommentDAO dao = new CommentDAO();
			int result = dao.commentWrite(dto);
			//System.out.println("처리결과 : " + result);
			// 이동하기
			
			if (result == 1) {
				response.sendRedirect("./detail?no=" + bno);
			} else {
				response.sendRedirect("./error.jsp");				
			}

		} else {
			response.sendRedirect("./error.jsp");
		}
		
	}

}
