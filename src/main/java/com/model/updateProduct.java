package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class updateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ServletContext ctx = getServletContext();
		String driver = ctx.getInitParameter("dbDriver");
		String user = ctx.getInitParameter("dbName");
		String url = ctx.getInitParameter("dbUrl");
		String pass = ctx.getInitParameter("dbPass");

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "UPDATE products SET prod_Name = ?, Category = ?, prod_price = ?, prod_ManufacturingDate = ? WHERE prod_Id = ?";
			PreparedStatement ps = conn.prepareStatement(query);

			String prodName = request.getParameter("prod_name");
			String category = request.getParameter("category");
			String price = request.getParameter("price");
			String manufacturingDate = request.getParameter("manufacturing_date");
			String uId = request.getParameter("uId");

			if (prodName != null && category != null && manufacturingDate != null) {
				if (!price.isEmpty() && !uId.isEmpty()) {
					ps.setString(1, prodName);
					ps.setString(2, category);
					ps.setInt(3, Integer.parseInt(price));
					ps.setDate(4, Date.valueOf(manufacturingDate));
					ps.setInt(5, Integer.parseInt(uId));

					ps.executeUpdate();

					HttpSession session = request.getSession();
					String userName = (String) session.getAttribute("userId");

					out.println("Data Updated by " + userName);
					out.println("<h1><a href=showAll.html>ShowAll</a></h1>");
					System.out.println(prodName + " " + category + " " + price + " " + manufacturingDate + " " + uId);
				}
			} else {
				out.println("One or more parameters are null<br> <h1>Record not updated </h1>");
				System.out.println(prodName + " " + category + " " + price + " " + manufacturingDate + " " + uId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}