package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;

@WebServlet("/idCheck")
public class IDCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IDCheck() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MemberDTO dto = new MemberDTO();
		dto.setMid(id);
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.idcheck(dto);		
		
		//System.out.println("검사요청이 들어온 id : " + id + " 결과는 : " + result);
		//1이면 중복 아이디가 있는거고 0이면 없는것 ! 횐가입 가능!
		
		//예에에전에 한번 했음 결과가 하나니까 보내줌
		PrintWriter pw = response.getWriter();
		pw.print(result);
	}

}
