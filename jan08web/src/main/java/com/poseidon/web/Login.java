package com.poseidon.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Receiver;

import com.poseidon.dao.LogDAO;
import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;
import com.poseidon.util.Util;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String url = null;
		
		if (session.getAttribute("mname") != null) {
			url = "already.jsp";
		} else {
			url = "login.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		
		if (request.getParameter("id") != null && request.getParameter("pw") != null) {
			MemberDTO dto = new MemberDTO();
			dto.setMid(request.getParameter("id"));
			dto.setMpw(request.getParameter("pw"));
			
			//DB에 일 시키려면 DAO만들어야 함
			
			MemberDAO dao = new MemberDAO();
			dto = dao.login(dto);
			
			// 24-1-23 아이피도 저장하기
			Map<String, Object> log = new HashMap<String, Object>();
			log.put("ip", Util.getIP(request));
			log.put("url", "./login");
			log.put("data", "id:" + dto.getMid() + ", pw:" + dto.getMpw() + " 결과:" + dto.getCount());
			
			LogDAO logDAO = new LogDAO();
			logDAO.write(log);
			
			
			
			if(dto.getCount() == 1) {
				
				if(dto.getMgrade() >= 5) {
					
					System.out.println("정상 로그인");
					// 세션 만들기(겁나 어려움)
					// 로그인을 하고 있냐 없냐 감시, 로그인 상태 유지 시간 정하기 등...
					HttpSession session = request.getSession();
					session.setAttribute("mname", dto.getMname()); //mname이라는 이름으로 세션 만듦
					session.setAttribute("mid", dto.getMid());  // mid라는 이름으로 세션 만듦
				
					// 페이지 이동 = board
					response.sendRedirect("./board");
				} else {
					System.out.println("로그인 금지 회원");
					int block = 1;
					request.setAttribute("blocked", block);
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
				
			} else {
				System.out.println("로그인 실패");
				//페이지 이동 = login?error=4567 이 숫자는 뭐지
				//아하 일단 에러의 값을 4567로 설정한것!
				//주소는 ? 앞까지가 끝
				response.sendRedirect("./login?error=4567");
			}
			
		} else {
			response.sendRedirect("./login?error=4567");			
		}

	}

}
