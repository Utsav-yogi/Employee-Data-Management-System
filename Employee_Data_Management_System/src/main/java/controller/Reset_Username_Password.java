package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reset")
public class Reset_Username_Password extends HttpServlet 
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
		
		String OldUname = request.getParameter("olduname1");
		String OldPassword = request.getParameter("oldpassword1");
		
		String NewUname = request.getParameter("newuname1");
		String CheckUname = request.getParameter("checkuname1");
		
		String NewPassword = request.getParameter("newpassword1");
		String CheckPassword = request.getParameter("checkpassword1");
		
		if(NewUname.equals(CheckUname) && NewPassword.equals(CheckPassword))
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_employee","root","Utsav@1999");
				PreparedStatement ps = con.prepareStatement("UPDATE emp_detail SET UserName=?, Password=? WHERE UserName=? AND Password=?");
				ps.setString(1, NewUname);
				ps.setString(2, NewPassword);
				ps.setString(3, OldUname);
				ps.setString(4, OldPassword);
				
				int count = ps.executeUpdate();
				
				if(count > 0)
				{
					RequestDispatcher rd = request.getRequestDispatcher("/reset_password_success.html");
					rd.include(request, response);
				}
				else
				{
					out.print("<h1 style='color: red; margin-left: 690px; margin-top: 70px; margin-bottom: 0px;'>Error!</h1>");
					out.print("<h4 style='color:red; margin-left: 530px; margin-top: 0px; margin-bottom: 0px;'>Please enter correct old username and password</h4>");
					
					RequestDispatcher rd = request.getRequestDispatcher("/reset_password.html");
					rd.include(request, response);
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				out.print("<h1 style='color: red; margin-left: 690px; margin-top: 30px; margin-bottom: 0px;'>Error!</h1>");
				out.print("<h4 style='color:red; margin-left: 525px; margin-top: 0px; margin-bottom: 0px;'>Please enter correct old username and password</h4>");
				
				RequestDispatcher rd = request.getRequestDispatcher("/reset_password.html");
				rd.include(request, response);
			}
		}
		
		else
		{
			out.print("<h1 style='color: red; margin-left: 690px; margin-top: 70px; margin-bottom: 0px;'>Error!</h1>");
			out.print("<h4 style='color:red; margin-left: 525px; margin-top: 0px; margin-bottom: 0px;'>Please enter correct new username and password</h4>");
			
			RequestDispatcher rd = request.getRequestDispatcher("/reset_password.html");
			rd.include(request, response);
		}
		
	}
 
}
