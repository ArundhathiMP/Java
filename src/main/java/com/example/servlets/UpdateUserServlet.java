package com.example.servlets;

import jakarta.servlet.ServletException;



import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
   
   public void init() {
	   
	try {
	    Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	   
   }	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("psw");
		
		try {
			Statement statement =connection.createStatement();
			int result = statement.executeUpdate("update user set password='"+password+"' where email='"+email+"'");
			PrintWriter out =response.getWriter();
			if(result>0) {
				out.print("<h1>Password Updated</h1>");
			}
			else {
				out.print("<h1>error creating the user</h1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
