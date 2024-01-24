package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;


@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글 삭제하기 
		//번호 잡기 - 글 번호가 파라미터로 온다
		//int no = Integer.parseInt(request.getParameter("no"));
		
		//util을 이용해서 스트링으로 오는 번호값을 숫자로 변환해주는 메소드를 만들어서 사용할 것임
			// 29일과 달리 "no" 를 int로 받는 이유 : 보안을 위함.
			// 36번 글의 삭제버튼을 누르면 detail.jsp의 del()메소드를 통해 delete?no=36로 이동하게 됨
			// delete?no=뒤에 문자를 넣거나, 범위 외의 숫자를 넣는 등의 장난을 치면 오류가 나면서 자바 코드가 다 보이게 됨
			// String 타입으로 받으면 숫자나 문자가 전부 문자열로 들어와서 구분이 안됨
			// int 타입으로 받으면 숫자와 문자가 구분되어 잘못된 입력을 조건에 따라 걸러낼 수 있음

		//no가 숫자인가? + 로그인 한 사용자인가 여부 검사 추가

		HttpSession session = request.getSession();
		
		if(Util.intCheck(request.getParameter("no")) && session.getAttribute("mid") != null) {
			
			//번호 잡기
			int no = Util.str2Int(request.getParameter("no"));			
			
			//DAO에게 일 시키기
			BoardDAO dao = new BoardDAO();
			// no(board_no), mid가 같이 있는 클래스는 BoardDTO
			BoardDTO dto = new BoardDTO();
			dto.setNo(no);
			dto.setMid((String)(session.getAttribute("mid")));
			
			int result = dao.delete(dto);
			
			//페이지 이동
			if (result == 1) {
				//값이 1이면 (삭제완료) board로 이동
				response.sendRedirect("./board");				
			} else {
				//값이 0이면 에러페이지로
				response.sendRedirect("./error.jsp");	
			}				
		} else {
			//no가 숫자가 아니라면 에러페이지로
			response.sendRedirect("./error.jsp");
		}								
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
