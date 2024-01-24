package com.poseidon.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.LogDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;


@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Detail() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//오는 no 잡기
		// int타입으로 변경
		//int no = Integer.parseInt(request.getParameter("no"));				
		
//01.11 util메소드로 변경
		int no = Util.str2Int(request.getParameter("no"));

//01.23 log
		LogDAO log = new LogDAO();
//		log.write(Util.getIP(request), getServletName(), "no=" + no);
		log.write(Util.getIP(request), "./detail", "no=" + no);
		
		
		//데이터베이스에 질의하기
		BoardDAO dao = new BoardDAO();
		
//01.19 읽음 수 올리기 (프레임워크 프로그래밍)
		//bno, mno값 있어야 조회수 측정가능
		//로그인한 회원이라면 읽음 수 올리기 - 로그인 한 경우에만 아래 로직 실행하고, 로그인안한 경우 바로 아래로 내려가기
		HttpSession session = request.getSession();
		
		if(session.getAttribute("mid") != null) {
			//bno, mno
			dao.countUp(no, (String)session.getAttribute("mid"));
		}
		
		
		BoardDTO dto = dao.detail(no);
		
//01.11 dto가 비었다는걸 어떻게 체크할까?
		//System.out.println(dto);
		// 주소값 나옴
		//System.out.println(dto == null);
		//false 나옴 (객체 생성했으니까)
		
		//dto.title이나 이런 값이 null이면 됨
		//System.out.println(dto.getTitle() == null);
		//true
		
		//null이면 에러페이지로 가게 하기
		//문자만일때 : no == 0
		//현재 없는 글 번호일때 : dto.getTitle() == null
		if (no == 0 || dto.getTitle() == null) {
			response.sendRedirect("./error.jsp");
		} else {
			//정상 출력
			//내용 가져오기
			request.setAttribute("detail", dto);
			
	//24 - 1 - 22 댓글이 있다면 list 뽑아내기
			List<CommentDTO> commentList = dao.commentList(no);
			
			if(commentList.size() > 0) {
				request.setAttribute("commentList", commentList);
			}
			
			
			//RequestDispatcher 호출하기
			RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
			rd.forward(request, response);
		}
		
		//파라미터에 엄청 큰 수를 넣으면 500에러가 나는 이유는 뭔가요..?
		//int 값을 넘어가서그렇습니다

		
		
		//내용 가져오기
		//request.setAttribute("detail", dto);
		//RequestDispatcher 호출하기
		//RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
		//rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
