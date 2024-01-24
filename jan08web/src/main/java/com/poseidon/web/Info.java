package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.LogDAO;
import com.poseidon.util.Util;


@WebServlet("/info")
public class Info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Info() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogDAO log = new LogDAO();
		log.write(Util.getIP(request), "./info", null);
		
		RequestDispatcher rd = request.getRequestDispatcher("info.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}	

}
