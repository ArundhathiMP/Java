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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadUserServlet extends HttpServlet {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			Statement statement =connection.createStatement();
			ResultSet rs=statement.executeQuery("select * from user");
			PrintWriter out=response.getWriter();
			out.print("<html>");
			out.print("<form action = \"readServlet\" method=\"post\">");
			out.println("<table border=\"1\">");
		    out.println("<tr>");
		    out.println("<th>First Name</th>");
		    out.println("<th>Last Name</th>");
		    out.println("<th>Email</th>");
		    out.println("</tr>");
		    while (rs.next()) {
		        out.println("<tr>");
		        out.println("<td>" + rs.getString(1) + "</td>");
		        out.println("<td>" + rs.getString(2) + "</td>");
		        out.println("<td>" + rs.getString(3) + "</td>");
		        out.println("</tr>");
		    }
		    out.print("</form>");
		    out.println("</table>");
		    out.print("</html>");
			
			while(rs.next()) {
				
				out.println("<tr>");	
				out.println("<td>");
				out.print(rs.getString(1));
				out.println("</td>");
				out.println("<td>");
				out.print(rs.getString(2));
				out.println("</td>");
				out.println("<td>");
				out.print(rs.getString(3));
				out.println("</td>");
				out.println("</tr>");
				
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
