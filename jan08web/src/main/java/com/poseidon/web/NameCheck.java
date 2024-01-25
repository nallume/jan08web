package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.CoffeeDAO;
import com.poseidon.dto.CoffeeDTO;

@WebServlet("/nameCheck")
public class NameCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public NameCheck() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		CoffeeDTO dto = new CoffeeDTO();		
		dto.setClName(request.getParameter("name"));
		
		CoffeeDAO dao = new CoffeeDAO();
		result = dao.nameCheck(dto);
		
		PrintWriter pw = response.getWriter();
		pw.print(result);
	}

}
