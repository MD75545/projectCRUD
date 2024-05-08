package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class deleteProduct extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletContext ctx = getServletContext();
        String driver = ctx.getInitParameter("dbDriver");
        String user = ctx.getInitParameter("dbName"); // Changed from "dbName" to "dbUser"
        String url = ctx.getInitParameter("dbUrl");
        String pass = ctx.getInitParameter("dbPass");

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pass);
            String query = "DELETE FROM products WHERE prod_Id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(request.getParameter("rId")));
            int affectedrows = ps.executeUpdate();
            if (affectedrows == 1) { // Changed the condition
                out.println("<h1>Record successfully deleted</h1>");
                out.println("<h1><a href=showAll.html>ShowAll</a></h1>");
            } else {
                out.println("<h1>Records not deleted</h1>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<h1>Error occurred: " + e.getMessage() + "</h1>"); // Communicate error to user
            e.printStackTrace(); // Log the error
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}