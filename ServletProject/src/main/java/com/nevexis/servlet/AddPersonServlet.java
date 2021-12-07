package com.nevexis.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nevexis.dao.PersonDao;
import com.nevexis.model.Person;

@WebServlet(description = "Tova e nashiyat servlet 1 ze testuvane", urlPatterns = { "/addPerson" })
public class AddPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/addPerson.jsp").forward(req, resp);
	}
   
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		try {
			PersonDao.insertPersons(new Person(id,name));
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		doGet(request, response);
	}

}
