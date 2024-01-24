package com.poseidon.web;

import java.io.IOException;

import javax.accessibility.AccessibleExtendedText;
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

@WebServlet("/write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Write() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("mname") == null) {
			response.sendRedirect("./login"); //url까지 변경해서 화면 보여줌
			//RequestDispatcher rd = request.getRequestDispatcher("write.jsp");
			// 얠 쓰면 화면만 로그인이고 주소창은 여전히 write로 나옴
			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("write.jsp"); //url고정. 화면만 변경
			rd.forward(request, response);			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리
		request.setCharacterEncoding("UTF-8");

		//세션에 들어있는 mid 가져오기
		HttpSession session = request.getSession();
		
		//if문으로 로그인되어있는(세션이 있는) 사람만 아래 로직 수행하도록(글을 쓰도록) 변경
		if(session.getAttribute("mid") == null || session.getAttribute("mname") == null) {
			//로그인하지 않았다면 login으로 가도록
			response.sendRedirect("./login?login=nologin");
		} else {
			//로그인 했다면 아래 로직을 수행하도록
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			//html 태그를 특수기호로 변경
			title = Util.removeTag(title);
			//줄바꿈은 안잡아줄거임 제목은 그런거 필요없으니까
			//내용은 서머노트가 다 알아서 해줌
			
			//DAO에 write 메소드 만들기
			BoardDTO dto = new BoardDTO();
			dto.setTitle(title);
			dto.setContent(content);
			//mid함께 dao로 보내기
			dto.setMid((String)(session.getAttribute("mid")));
			
			//작성자 ip 가져오기
			dto.setIp(Util.getIP(request));
			
			BoardDAO dao = new BoardDAO();
			int result = dao.write(dto);
			
			// 페이지 이동하기 = url값과 화면 모두 이동하기
			// forward와의 차이점
			if (result == 1) {
				response.sendRedirect("./board");
			} else {
				response.sendRedirect("./error.jsp");
			}
		}
		
	}

}
