package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Update() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//세션이 있을 때 = 정상 작업하기
		if (session.getAttribute("mid") != null && Util.intCheck(request.getParameter("no"))) {
			int no = Util.str2Int(request.getParameter("no"));
			
			// DAO에게 질의하기
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = dao.detail(no);
			
			//이 글의 mid와 현재 로그인된 mid가 같으면 그때 수정 실행
			if (dto.getMid().equals(session.getAttribute("mid"))) {
				request.setAttribute("update", dto);
				RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
				rd.forward(request, response);
				
			} else {
				response.sendRedirect("./error.jsp");
			}
			
		} else {
			response.sendRedirect("./login?login=nologin");
		}

			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//숫자인지 & 내용이 null이 아닌지 검사
		if (request.getParameter("title") != null && request.getParameter("content") != null 
				&& Util.intCheck(request.getParameter("no")) && session.getAttribute("mid") != null) {
			//진짜 수정
			BoardDTO dto = new BoardDTO();
			dto.setTitle(request.getParameter("title"));
			dto.setContent(request.getParameter("content"));
			dto.setNo(Util.str2Int(request.getParameter("no")));
			dto.setMid((String)session.getAttribute("mid"));
			
			BoardDAO dao = new BoardDAO();
			int result = dao.update(dto);
			//System.out.println("수정결과 : " + result);
			
			if (result == 1) {
				response.sendRedirect("./detail?no=" + request.getParameter("no"));
			} else {
				response.sendRedirect("./error.jsp");				
			}
			
		} else {
			//error
			response.sendRedirect("./error.jsp");
		}
		
	}

}
