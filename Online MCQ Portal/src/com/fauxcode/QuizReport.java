package com.fauxcode;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class QuizReport
 */
public class QuizReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		
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
		
		
		response.setContentType("text/html");
		pw.println("<style> body{ background-image:url('a7.jpg'); background-size:cover; }  h3{ color:#204056; } @media only screen and (max-width: 600px) {\r\n" + 
				"    .card {\r\n" + 
				"        margin-bottom:40px;\r\n" + 
				"    }</style>");
		
		RequestDispatcher rd = request.getRequestDispatcher("quiz_report.html");
		rd.include(request, response);
		
		ResultSet rs;
		ResultSet rs2;
		try
		{
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			 
			 PreparedStatement ps = con.prepareStatement("select * from student where name='"+session.getAttribute("student")+"'");
			 rs2 =  ps.executeQuery();
			 rs2.next();
			 PreparedStatement ps2 = con.prepareStatement("select * from quizsetting");
			 rs =  ps2.executeQuery();
			 rs.next();
		
		
		pw.println("<div class='container'>\r\n" + 
				"	<div class='row mt-5'> \r\n" + 
				"		<div class='col-md-5 mx-auto'>"
			+ "<div class='card card-header bg-light'>"
				+ "<h2><div class='text-danger'><center>Quiz Report</center></div></h2>"
			+ "</div>" 
				+"<div class='card card-body'>");
		
		if(rs2.getInt(3)==0)
		{
		
			if(rs2.getInt(5)==-1)
			{
				pw.println(
					    
						"<h4><div class='mt-4'>Quiz Date : "+rs.getDate(1).toString()+"</div></h4>"
						+ "<h4><div class='mt-4'>Quiz Time : "+rs.getString(2)+" Min</div></h4>"
						+ "<h4><div class='mt-4'>Your Marks : Not available</div></h4> <h6>closed browser without saving</h6>"
						+ "<h4><div class='mt-4'>Total Marks : Quiz Not Submitted</div></h4>"
						+ "<h4><div class='mt-4'>You have Alredy Attempted Quiz</div></h4>"
						
						
			);
			}
			else
			{
				pw.println(
			    
							"<h4><div class='mt-4'>Quiz Date : "+rs.getDate(1).toString()+"</div></h4>"
							+ "<h4><div class='mt-4'>Quiz Time : "+rs.getString(2)+" Min</div></h4>"
							+ "<h4><div class='mt-4'>Your Marks : "+rs2.getString(4)+"</div></h4>"
							+ "<h4><div class='mt-4'>Total Marks : "+rs2.getString(5)+"</div></h4>"
							+ "<h4><div class='mt-4'>You have Alredy Attempted Quiz</div></h4>"
				);
			}
		}
		else
		{
			pw.println(
				    
					"<h4><div class='mt-4'>Quiz Date : "+rs.getDate(1).toString()+"</div></h4>"
					+ "<h4><div class='mt-4'>Quiz Time : "+rs.getString(2)+" Min</div></h4>"
					+ "<h4><div class='mt-4'>You have not Attempted Quiz Yet!</div></h4>"
					+"<a href='/Online_MCQ_Portal/AvailableQuiz1' class='btn btn-secondary mt-5 mb-4'>Attempt Quiz</a>"
		);
		}
		
		
		pw.println("</div>	</div>\r\n" + 
				"	</div>\r\n" + 
				"</div>");
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
