package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//세션 종료
		/* 세션
		 * 세션과 쿠키
		 * 세션 : 서버에 저장된다. (우리에게는 톰캣.)
		 *        로그인 정보 등
		 *        자바
		 *        은닉형태
		 * 
		 * 쿠키 : 클라이언트에 저장 (사용자 컴퓨터 브라우저 - 보안이 취약)
		 * 	      사용 기록 (쇼핑정보, 장바구니, 방문 내역)
		 *        스크립트
		 *        오픈형태
		 */
		HttpSession session = request.getSession();
		
		//mid, mname이 있을때만 세션을 종료 시키기 위함
		if(session.getAttribute("mname") != null) {
			//System.out.println("세션 유효시간 : " + session.getMaxInactiveInterval());
			//System.out.println("mname : " + session.getAttribute("mname"));

			//session.setMaxInactiveInterval(3600); 세션 시간 연장

			//세션 진짜 종료
			session.removeAttribute("mname");
		}
		if (session.getAttribute("mid") != null) {
			//System.out.println("mid : " + session.getAttribute("mid"));			
			
			//종료2
			session.removeAttribute("mid");
		}
		
		//또 다른 방법 
		session.invalidate();
		//해당 브라우저에 연결된 모든 세션을 지우는 메소드
		//mname, mid의 세션을 개별적으로 삭제하지 않고 한방에 다 중지 해버림
		//invalidate()는 세션 자체를 무효화, 제거하고
		//removeAttribute()는 현재 세션에서 특정 key-value만 제거를 한다.
		//removeAttribute()로 키만 제거를 하면 HttpSession 인스턴스가 남아있게 되어서
		//invalidate() 사용을 더 권장하는 책도 있음
		
		//logout페이지로 보내기
		//response.sendRedirect("./logout.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("logout.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
