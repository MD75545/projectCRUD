package com.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ExtraActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		out.println("<h1><a href=addProd.html>Add Product</a></h1>");
		out.println("<h1><a href=removeId.html>RemoveById</a></h1>");
		out.println("<h1><a href=updateId.html>updateById</a></h1>");
		out.println("<h1><a href=showAll.html>ShowAll</a></h1>");
		
	}

}
