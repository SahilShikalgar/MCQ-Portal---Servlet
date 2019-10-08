package com.fauxcode;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AvailableQuiz1
 */



public class AvailableQuiz1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvailableQuiz1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
		
		
		ResultSet rs;
		try
		{
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			 
			 PreparedStatement ps = con.prepareStatement("select * from quizsetting where id='1'");
			 rs =  ps.executeQuery();
			 rs.next();
			 
			 PreparedStatement ps1 = con.prepareStatement("select * from questions");
			 ResultSet rs2 =  ps1.executeQuery();
			 int marks=0,cnt=0;
			 
			 while(rs2.next())
			 {
				 marks=marks+rs2.getInt(2);
				 cnt++;
			 }
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		pw.println("<style> body{ background-image:url('a7.jpg'); background-size:cover; }  h3{ color:#204056; } </style>");
		
		pw.println("<script> function Confirm() {\r\n" + 
				"    var txt;\r\n" + 
				"    var r = confirm(\"Really want to take test?\");\r\n" + 
				"    if (r == true) {\r\n" + 
				"        alert('ok'); "+
				"					   "+
				"    } else {\r\n" + 
				"       alert('no'); "+ 
				"    }\r\n" + 
				"} </script>");
		
		
		
		
	/*	pw.println("<script>\r\n" + 
				"var myWindow;\r\n" + 
				"function openWin() {\r\n" + 
				" myWindow = window.open('ViewQuiz', \"blank\", \"width=1920, height=1080\");\r\n" + 
				"}\r\n" + 
				"</script>");*/
		
	
		
	/*	pw.println("<script> \r\n" + 
				"	var myWindow;\r\n" + 
				"	function openWin() \r\n" + 
				"	{\r\n" + 
				"		if(document.getElementById('password').value=="+rs.getString(3)+")\r\n" + 
				"		{\r\n" + 
				"\r\n" + 
				"		myWindow = window.open('ViewQuiz', \"blank\",\"width=1920, height=1080\");\r\n" + 
				"		} \r\n" + 
				"		else\r\n" + 
				"		{\r\n" + 
				"			alert('enter correct password to start quiz');\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"</script>");	*/
		
	//	String dt = rs.getDate(1).toString();
		pw.println("<script> \r\n" + 
				"	var myWindow;\r\n" + 
				"	function openWin() \r\n" + 
				"	{var d = new Date();\r\n" + 
				"						month = '' + (d.getMonth() + 1),\r\n" + 
				"				         day = '' + d.getDate(),\r\n" + 
				"				         year = d.getFullYear(); \r\n" + 
				"				 \r\n" + 
				"				 	     if (month.length < 2) month = '0' + month;\r\n" + 
				"				         if (day.length < 2) day = '0' + day;\r\n" + 
				"			 \r\n" + 
				"				    	var today = [year, month, day].join('-');\r\n" + 
				"		if(document.getElementById('password').value=="+rs.getString(3)+")\r\n" + 
				"		{\r\n" + 
				"\r\n" + 
				"		        if(today==document.getElementById('date').innerHTML)\r\n" + 
				"				{  myWindow = window.open('ViewQuiz', \"blank\",\"width=1920, height=1080\");} else{alert('quiz is not available currently');} \r\n" + 
				"		} \r\n" + 
				"		else\r\n" + 
				"		{\r\n" + 
				"			alert('enter correct password to start quiz');\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"</script>");	
		
		
		RequestDispatcher rd = request.getRequestDispatcher("available_quiz.html");
		rd.include(request, response);
		
		pw.println("<div class='container'>	"
					+ "<div class='row mt-5'> "
						+ "<div class='col-md-5 mx-auto'> "
							+ "<div class='card mb-5'>"
								+ "<div class='card-header'>"
									+ "<h3><div class='text-danger'><center>Online MCQ Portal</center></div></h3>"
								+ "</div>"
									
				//				+"<form action='ViewQuiz' method='post'>"
								+ "<div class='card-body'>"
											+ "<h5><div class='mt-4 d-inline'>Quiz Date : <p id='date' class='d-inline'>"+rs.getDate(1).toString()+"</p></div></h5>"
											+ "<h5><div class='mt-4'>Quiz Time : "+rs.getString(2)+" Min</div></h5>"
											+ "<h5><div class='mt-4'>Quiz Password : "+rs.getString(3)+"</div></h5>"
											+ "<h5><div class='mt-4'>Quiz Marks : "+marks+"</div></h5>"
											+ "<h5><div class='mt-4'>Number of Questions : "+cnt+"</div></h5>"
											+ "<h5 class='mt-4'>Enter Password : </h5>"
											+"<input id='password' type='password' name='password' required='true' class='form-control'>"
											+"<input type='submit' class='btn btn-secondary mt-4 form-control' value='Attempt Quiz' name='Attempt Quiz' onclick='openWin();'>"
								+ "</div>"
						//		+"</form>"
							+ "</div> "
						+ "</div> "
					+ "</div>"
				+"</div>");
		//System.out.println(dt);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
