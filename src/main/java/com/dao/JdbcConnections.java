package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcConnections {
	public ResultSet validateLogin(String userName, String Password) throws ClassNotFoundException, SQLException {
		ResultSet rs = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ronaldo", "root", "cr7@7");
		PreparedStatement stat = con.prepareStatement("select * from employees where first_name=? AND last_name=?");
		stat.setString(1, userName);
		stat.setString(2, Password);
		rs = stat.executeQuery();
		System.out.println(userName);
		System.out.println(Password);
		if (rs.next())
			return rs;
		else {
			return null;
		}

	}
}
