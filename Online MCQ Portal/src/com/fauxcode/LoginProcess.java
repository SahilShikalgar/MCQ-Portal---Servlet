package com.fauxcode;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginProcess
 */
public class LoginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
		 PrintWriter pw = response.getWriter(); 
		 String btn = request.getParameter("login");
		 String check="";
		 if(btn.equals("Log In"))
		 {
			 check="admin";
		 }
		 else if(btn.equals("Login"))
		 {
			 check="student";
		 }
		 

		HttpSession session = request.getSession(true);
		 
		 try
		 {  
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			 Statement stmt=con.createStatement(); 
			 ResultSet rs;
			 int flag=0;
			 String user="";
			 
			 if(check.equals("admin"))
			 {
				 rs = stmt.executeQuery("select * from admin"); 
				 while(rs.next()) 
				 {  
				 	if(rs.getString(1).equals(request.getParameter("admin_name")) && rs.getString(2).equals(request.getParameter("admin_password")))
				 	{
				 		//System.out.println(rs.getString(1)+" "+rs.getString(2));
				 		user=rs.getString(1);
				 		flag=1;
				 	}
				 }
				 if(flag==1)
				 {		
					 session.setAttribute("admin",user);
					 pw.println("<script>\r\n" + 
					 		"					if (typeof(Storage) !== 'undefined') \r\n" + 
					 		"					{	 \r\n" + 
					 		"					    localStorage.setItem('admin','yes');\r\n" + 
					 		"					}\r\n" + 
					 		"					else\r\n" + 
					 		"					{\r\n" + 
					 		"						alert('browser does not support web storage');\r\n" + 
					 		"					}\r\n" + 
					 		"					</script>");
					

					 pw.println("<script> alert('Login Succeed'); window.location='admin_dashboard.html' </script>");
				 }
				 else if(flag==0)
				 {
					 pw.println("<script> alert('Login Failed'); window.location='index.html'</script>");
				 }
			 }
			 else
			 {
				 rs = stmt.executeQuery("select * from student");
				 while(rs.next()) 
				 {  
				 	if(rs.getString(1).equals(request.getParameter("student_roll")) && rs.getString(2).equals(request.getParameter("student_password")))
				 	{
				 		user=rs.getString(1);
				 		flag=1;
				 	}
				 }
				 if(flag==1)
				 {	
					 session.setAttribute("student",user);
					 
					 pw.println("<script>\r\n" + 
					 		"					if (typeof(Storage) !== 'undefined') \r\n" + 
					 		"					{	 \r\n" + 
					 		"					    localStorage.setItem('user','yes');\r\n" + 
					 		"					}\r\n" + 
					 		"					else\r\n" + 
					 		"					{\r\n" + 
					 		"						alert('browser does not support web storage');\r\n" + 
					 		"					}\r\n" + 
					 		"					</script>");
					 
					 pw.println("<script> alert('Login Succeed'); window.location='student_dashboard.html' </script>");
				 }
				 else if(flag==0)
				 {
					 pw.println("<script> alert('Login Failed'); window.location='index.html'</script>");
				 }
			 }
			 con.close();  
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }    
	}
}
