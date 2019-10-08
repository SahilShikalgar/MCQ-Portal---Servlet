package com.fauxcode;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class calculateMarks
 */
public class calculateMarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public calculateMarks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.sendRedirect("index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		HttpSession session = request.getSession(false);
		try
		{
			if(session!=null)
			{
				if(session.getAttribute("student").equals(""))
				{
					response.sendRedirect("index.html");
				}
			}
			else
			{
				response.sendRedirect("index.html");
			}
		}
		catch(Exception e)
		{
			response.sendRedirect("index.html");
		}
		

		PrintWriter pw = response.getWriter();
	/*	pw.println("<script type=\"text/javascript\">\r\n" + 
					"        window.history.forward(1);\r\n" + 
					"</script>");*/
		
		
		ResultSet rs;
		
		try
		{
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			 
			 PreparedStatement ps = con.prepareStatement("select * from questions");
			 rs =  ps.executeQuery();
			 String s="";
			 int marks=0;
			 int total=0;
			 while(rs.next())
			 {
				 if(rs.getString(8).equals(rs.getString(4)))
				 {
					 s="a";
				 }
				 else if(rs.getString(8).equals(rs.getString(5)))
				 {
					 s="b";
				 }
				 else if(rs.getString(8).equals(rs.getString(6)))
				 {
					 s="c";
				 }
				 else if(rs.getString(8).equals(rs.getString(7)))
				 {
					 s="d";
				 }
				
				 String output = request.getParameter(rs.getString(1));
				 System.out.println(s+" == "+output);
				 
				 if(s.equals(output))
				 {
					 marks= marks + rs.getInt(2);
				 }
				 total=total+rs.getInt(2);
			 }
			 System.out.println(marks);
			 
			 
			 PreparedStatement ps2 = con.prepareStatement("update student set marks='"+marks+"',total='"+total+"' where name='"+ session.getAttribute("student") +"'");
			 ps2.executeUpdate();
			 
			 pw.println("<script> alert('Quiz Submitted Successfully ! '); window.close(); </script>");
			 
	/*		 
// after calculation 
			
			 if(session==null)
				{
				}
				else
				{
					session.removeAttribute("student");
				}
				
		PrintWriter pw = response.getWriter();
				
				response.setContentType("text/html");
				
				pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"\r\n" + 
						"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css\">\r\n" + 
						"\r\n" + 
						"<!-- jQuery library -->\r\n" + 
						"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
						"\r\n" + 
						"<!-- Popper JS -->\r\n" + 
						"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js\"></script>\r\n" + 
						"\r\n" + 
						"<!-- Latest compiled JavaScript -->\r\n" + 
						"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js\"></script>\r\n" + 
						"");
				
				pw.println("<script>\r\n" + 
						"if (typeof(Storage) !== \"undefined\") \r\n" + 
						"{\r\n" + 
						"    localStorage.user = 'no';\r\n" + 
						"} \r\n" + 
						"else\r\n" + 
						"{\r\n" + 
						"   alert('Sorry, your browser does not support Web Storage...');\r\n" + 
						"}\r\n" + 
						"</script>");
				
				pw.println("<script> alert('Quiz Submitted Successfully ! '); window.location='index.html' </script>");
				
		*/	 
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}

}
