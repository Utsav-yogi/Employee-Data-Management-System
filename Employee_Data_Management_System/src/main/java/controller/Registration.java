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



@WebServlet("/regform")
public class Registration extends HttpServlet
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
	   
	   String FirstName = request.getParameter("firstname1");
	   String LastName = request.getParameter("lastname1");
	   String Email = request.getParameter("email1");
	   String Address = request.getParameter("address1");
	   String City = request.getParameter("city1");
	   String State = request.getParameter("state1");
	   String Gender = request.getParameter("gender1");
	   String UserName = request.getParameter("username1");
	   String CheckUsername = request.getParameter("username2");
	   String Password = request.getParameter("password1");
	   String CheckPassword = request.getParameter("password2");
	   
	   if(UserName.equals(CheckUsername) && Password.equals(CheckPassword))
	   {
	     try
	     {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_employee","root","Utsav@1999");
		   PreparedStatement ps = con.prepareStatement("INSERT INTO emp_detail VALUES(?,?,?,?,?,?,?,?,?)");
		   ps.setString(1, FirstName);
		   ps.setString(2, LastName);
		   ps.setString(3, Email);
		   ps.setString(4, Address);
		   ps.setString(5, City);
		   ps.setString(6, State);
		   ps.setString(7, Gender);
		   ps.setString(8, UserName);
		   ps.setString(9, Password);
		   
		   int count = ps.executeUpdate();
		   
		   if(count > 0)
		   {
			   out.print("<h1 style='color:green; margin-top: 40px; margin-left: 660px; position: absolute; top: 0px;'>Successfull</h1>");
			   out.print("<h3 style='color:green; margin-top: 10px; margin-left: 620px; position: absolute; top: 70px;'>Data inserted successfully</h3>");
			   
               RequestDispatcher rd = request.getRequestDispatcher("/login.html");
               rd.include(request, response);

		   }
		   else
		   {
			   out.print("<h3 style='color:red; margin-left: 690px; position: relative; top: 20px;'>Error!</h3>");
			   out.print("<h3 style='color: red; margin-left: 630px;'>Please insert correct data</h3>");
			   
			   RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
			   rd.include(request, response);
		   }
		   
		   //con.close();
	      }
	      catch(Exception e)
	      {
		     e.printStackTrace();
		   
		     out.print("<h1 style='color: red; margin-left: 690px; position: relative; top: 20px;'>Error!</h1>");
		     out.print("<h3 style='color: red; margin-left: 630px;'>Please insert correct data</h3>");
		   
		     RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
		     rd.include(request, response);
	      }
	   }
	   else
	   {
		   out.print("<h1 style='color: red; margin-left: 690px; position: relative; top: 20px;'>Error!</h1>");
		   out.print("<h3 style='color: red; margin-left: 585px;'>Please check username and password</h3>");
		   
		   RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
		   rd.include(request, response);
	   }
    }
}
