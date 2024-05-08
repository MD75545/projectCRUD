package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.JdbcConnections;

public class AddProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String url = "jdbc:mysql://localhost:3306/ronaldo";
		String dbUsername = "root";
		String dbPassword = "cr7@7";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// Connect to the database
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);

			String prodId = request.getParameter("prod_id");
			String prodName = request.getParameter("prod_name");
			String category = request.getParameter("category");
			double price = Double.parseDouble(request.getParameter("price"));
			String manufacturingDate = request.getParameter("manufacturing_date");

			// Insert into products table
			String Query = "INSERT INTO products (prod_Id,prod_Name, Category, prod_price, prod_ManufacturingDate) VALUES (?, ?, ?, ?,?)";
			pstmt = conn.prepareStatement(Query);
			pstmt.setString(1, prodId);
			pstmt.setString(2, prodName);
			pstmt.setString(3, category);
			pstmt.setDouble(4, price);
			pstmt.setString(5, manufacturingDate);
			pstmt.executeUpdate();
			HttpSession session = request.getSession();
			String userName = (String) session.getAttribute("userId");

			
			out.println("Data Added by " + userName);
			out.println("<h1><a href=showAll.html>ShowAll</a></h1>"); 


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
