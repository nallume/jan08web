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

@WebServlet("/commentDel")
public class CommentDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentDel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("mid") != null && request.getParameter("no") != null && request.getParameter("cno") != null) {
			int no = Util.str2Int(request.getParameter("no"));
			int cno = Util.str2Int(request.getParameter("cno"));
			
			CommentDTO dto = new CommentDTO();
			dto.setBno(no);
			dto.setCno(cno);
			dto.setMid((String)session.getAttribute("mid"));
			
			CommentDAO dao = new CommentDAO();
			
			int result = dao.commentDel(dto);
			
			if(result == 1) {
				response.sendRedirect("./detail?no="+dto.getBno());				
			} else {
				response.sendRedirect("./error.jsp");
			}
						
		} else {
			response.sendRedirect("./error.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("요청이 들어왔습니다.");
		//System.out.println(request.getParameter("no"));
		
		HttpSession session = request.getSession();
		
		int result = 0;
		
		if(session.getAttribute("mid") != null && request.getParameter("cno") != null) {
			int cno = Util.str2Int(request.getParameter("cno"));
			
			CommentDTO dto = new CommentDTO();
			dto.setMid((String)session.getAttribute("mid"));
			dto.setCno(cno);
			
			CommentDAO dao = new CommentDAO();
			
			result = dao.commentDel(dto);												
		}
		
		PrintWriter pw = response.getWriter();
		pw.print(result);
		
		//삭제 됨 - result : 1, 아니면 0 이제 0인 경우 ajax에서 처리해줌
	}
	
}
