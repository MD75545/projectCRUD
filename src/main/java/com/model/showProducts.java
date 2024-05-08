package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class showProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ServletContext ctx = getServletContext();
		String driver = ctx.getInitParameter("dbDriver");
		String user = ctx.getInitParameter("dbName");
		String url = ctx.getInitParameter("dbUrl");
		String pass = ctx.getInitParameter("dbPass");

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from products");
			out.println("<h2>Product List</h2>");
			out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Category</th><th>Price</th><th>ManufacturingDate</th></tr>");
			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getInt("prod_Id") + "</td>");
				out.println("<td>" + rs.getString("prod_Name") + "</td>");
				out.println("<td>" + rs.getString("category") + "</td>");
				out.println("<td>" + rs.getDouble("prod_Price") + "</td>");
				out.println("<td>" + rs.getDate("prod_ManufacturingDate") + "</td>");
				out.println("</tr>");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
