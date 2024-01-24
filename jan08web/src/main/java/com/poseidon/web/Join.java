package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;

@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Join() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("mid") != null) {
			response.sendRedirect("./error.jsp");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
			rd.forward(request, response);			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//join에서 네가지 값이 날아옴 id, name, pw1, pw2... 그치만 어차피 둘이 일치해야 되니까 pw1만 잡으면 된다
		
		//값 받기
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw1");
		
		//db에 보내기
		MemberDTO dto = new MemberDTO();
		dto.setMid(id);
		dto.setMname(name);
		dto.setMpw(pw);
		
		MemberDAO dao = new MemberDAO();
		int result = dao.join(dto);
		
		//정상적으로 데이터 입력을 완료햇다면 로그인 페이지로, 비정상이라면 에러로
		
		if(result == 1) {
			response.sendRedirect("./login");
		} else {
			response.sendRedirect("./error.jsp");
		}
		
		
	}

}
