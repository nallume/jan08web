package com.poseidon.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.AdminDAO;
import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;
import com.poseidon.util.Util;

@WebServlet("/admin/members")
public class Members extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Members() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 전체 목록 가져오기
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();		
		List<Map<String, Object>> memberList = new ArrayList<Map<String,Object>>();
		
		MemberDAO dao = new MemberDAO();
		memberList = dao.memberList();
		
		AdminDAO adao = new AdminDAO();
		
//		if(request.getParameter("grade") != null || request.getParameter("grade").equals("")) {
		
		if(request.getParameter("grade") != null) {
			list = adao.list(Util.str2Int(request.getParameter("grade")));
			request.setAttribute("list", list);
		} else {
			list = adao.list();	
			request.setAttribute("list", list);
		}
				
		request.setAttribute("memberList", memberList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/members.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		//db에 저장
		MemberDTO dto = new MemberDTO();
		dto.setMno(Util.str2Int(request.getParameter("mno")));
		dto.setMgrade(Util.str2Int(request.getParameter("grade")));
		
		AdminDAO dao = new AdminDAO();
		
		result = dao.changeGrade(dto);
		
		//페이지 이동
		
		if(request.getParameter("currentgrade") == null || request.getParameter("currentgrade").equals("")) {
			response.sendRedirect("./members");
		} else {			
			if(result == 1) {
				response.sendRedirect("./members?grade=" + Util.str2Int(request.getParameter("grade")));	
			} else {
				System.out.println("땡");
				response.sendRedirect("./members?grade=" + Util.str2Int(request.getParameter("currentgrade")));
			}			
		}
	}

}
