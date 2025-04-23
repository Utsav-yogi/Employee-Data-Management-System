package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DataStorage;

@WebServlet("/loginform")
public class Login extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String UserName = request.getParameter("username1");
		String Password = request.getParameter("password1");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_employee","root","Utsav@1999");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM emp_detail WHERE UserName=? AND Password=?");
			ps.setString(1, UserName);
			ps.setString(2, Password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				DataStorage DS = new DataStorage();
				
				DS.setFirstName(rs.getString("FirstName"));
				DS.setLastName(rs.getString("LastName"));
				DS.setEmail(rs.getString("Email"));
				DS.setAddress(rs.getString("Address"));
				DS.setCity(rs.getString("City"));
				DS.setState(rs.getString("State"));
				DS.setGender(rs.getString("Gender"));
				
				HttpSession session = request.getSession();
				session.setAttribute("session_DS", DS);
				
				RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
				rd.forward(request, response);
			}
			else
			{
				out.print("<h1 style='color: red; margin-left: 690px; margin-top: 30px; position: absolute;'>Error!</h1>");
				out.print("<h4 style='color: red; margin-left: 555px; margin-top: 75px; position: absolute;'>Please enter correct username and password</h4>");
				
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.include(request, response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			out.print("<h3 style='color:red'>Error not login</h3>");
			
			RequestDispatcher rd = request.getRequestDispatcher("/login.html");
			rd.include(request, response);
		}
	
	}

}
