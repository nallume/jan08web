package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.CoffeeDAO;
import com.poseidon.dto.CoffeeDTO;
import com.poseidon.util.Util;

@WebServlet("/coffee")
public class Coffee extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Coffee() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CoffeeDAO dao = new CoffeeDAO();
		
		List<String> classMate = dao.ClassMate();
		Map<String, Integer> total = dao.total();
		
		request.setAttribute("classMate", classMate);
		request.setAttribute("total", total);
		
		RequestDispatcher rd = request.getRequestDispatcher("coffee.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		int menu = Util.str2Int(request.getParameter("menu"));
		int ice = Util.str2Int(request.getParameter("ice"));
		
		CoffeeDTO dto = new CoffeeDTO();
		dto.setClName(name);
		dto.setDrink(menu);
		dto.setIce(ice);
		
		CoffeeDAO dao = new CoffeeDAO();
		int result = dao.menu(dto);
		
		if (result == 1) {			
			PrintWriter pw = response.getWriter();
			pw.print(result);			
		} else {			
			System.out.println("요기?");
		}
		
	}

}
