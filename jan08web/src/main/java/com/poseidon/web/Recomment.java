package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/recomment")
public class Recomment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Recomment() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(request.getParameter("cno"));
//		System.out.println(request.getParameter("comment"));

		HttpSession session = request.getSession();
		int result = 0;
		if (session.getAttribute("mid") != null && Util.intCheck(request.getParameter("cno"))
				&& request.getParameter("comment") != null) {
			
			//댓글 내용
			String commentcontent = request.getParameter("comment");
			//댓글 내용에서 특수기호 <,>,변경하기 & 줄바꿈 처리해주기
									
			//html에서 태그를 특수기호로 변경하기
			commentcontent = Util.removeTag(commentcontent);
			
			//엔터처리 (줄바꿈) /r /n /nr
			commentcontent = Util.addBR(commentcontent);
			
			CommentDTO dto = new CommentDTO();
			dto.setMid((String) session.getAttribute("mid"));
			dto.setCno(Util.str2Int(request.getParameter("cno")));
			dto.setCcomment(commentcontent);

			CommentDAO dao = new CommentDAO();
			result = dao.commentUpdate(dto);
		}

		PrintWriter pw = response.getWriter();
		pw.print(result);

	}

}
