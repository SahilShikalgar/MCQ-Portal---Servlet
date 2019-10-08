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
 * Servlet implementation class ViewQuiz
 */
public class ViewQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//response.sendRedirect("index.html");
		doPost(request,response);
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
		
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		pw.println("<style> body{ background-image:url('a7.jpg'); background-size:cover; }  </style>");

		
//................................................new
		
		int pegi=0;
		int marks=0;
		
		try
		{
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			 
			 
			 PreparedStatement ps5 = con.prepareStatement("select * from questions");
			 ResultSet rs5 = ps5.executeQuery();
			 
			 while(rs5.next())
			 {
				pegi++; 
				marks = marks + rs5.getInt(2);
			 }
			 con.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		int no;
		if(pegi%5==0)
		{
			no=pegi/5;
		}
		else
		{
			no=pegi/5;
			no++;
		}
		
		
		
		pw.println("<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n" + 
				"<link href=\"pagination.css\" rel=\"stylesheet\">\r\n" + 
				"\r\n" + 
				"<script type=\"text/javascript\" src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\r\n" + 
				"<script type=\"text/javascript\" src=\"https://www.solodev.com/assets/pagination/jquery.twbsPagination.js\"></script>\r\n" + 
				"\r\n" + 
				"<link href=\"pagination.css\">");
		
		
		
		pw.println("<script type=\"text/javascript\">\r\n" + 
				"$(document).ready(function() {\r\n" + 
				"	$('#pagination-demo').twbsPagination({\r\n" + 
				"		totalPages: "+no+",\r\n" + 
				"		// the current page that show on start\r\n" + 
				"		startPage: 1,\r\n" + 
				"		// maximum visible pages\r\n" + 
				"		visiblePages: 5,\r\n" + 
				"		initiateStartPageClick: true,\r\n" + 
				"		// template for pagination links\r\n" + 
				"		href: false,\r\n" + 
				"		// variable name in href template for page number\r\n" + 
				"		hrefVariable: '{{number}}',\r\n" + 
				"		// Text labels\r\n" + 
				"		first: 'First',\r\n" + 
				"		prev: 'Previous',\r\n" + 
				"		next: 'Next',\r\n" + 
				"		last: 'Last',\r\n" + 
				"		// carousel-style pagination\r\n" + 
				"		loop: false,\r\n" + 
				"		// callback function\r\n" + 
				"	onPageClick: function (event, page) {\r\n" + 
				"		   $('.page-active').removeClass('page-active');\r\n" + 
				"		  $('#page'+page).addClass('page-active'); $('html, body').animate({scrollTop:0}, 700); \r\n" + 
				"		},\r\n" + 
				"		// pagination Classes\r\n" + 
				"		paginationClass: 'pagination',\r\n" + 
				"		nextClass: 'next',\r\n" + 
				"		prevClass: 'prev',\r\n" + 
				"		lastClass: 'last',\r\n" + 
				"		firstClass: 'first',\r\n" + 
				"		pageClass: 'page',\r\n" + 
				"		activeClass: 'active',\r\n" + 
				"		disabledClass: 'disabled'\r\n" + 
				"		});\r\n" + 
				"	});\r\n" + 
				"</script>");
		
		

		
		
		
		
		//
		
//....................................................new
		
		int time=2;
		
		try
		{
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			 
			 
			 PreparedStatement ps1 = con.prepareStatement("select * from quizsetting");
			 ResultSet rs1 = ps1.executeQuery();
			 rs1.next();
			 time=rs1.getInt(2);
			 
			 con.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		
		
		pw.println("\r\n" + 
				"    <script type=\"text/javascript\">\r\n" + 
				"    	function func()\r\n" + 
				"    	{\r\n" + 
				"    		alert('Cannot able to access this function during examination');\r\n" + 
				"    	}\r\n" + 
				"    </script>");
		
		
		pw.println(" <script type=\"text/javascript\">\r\n" + 
				"\r\n" + 
				"        $(document).ready(function() {\r\n" + 
				"            f1();\r\n" + 
				"        });\r\n" + 
				"\r\n" + 
				"        var tim=\"10\";\r\n" + 
				"       \r\n" + 
				"        var min = "+time+";\r\n" + 
				"        var sec = 2;\r\n" + 
				"        var f = new Date();\r\n" + 
				"        function f1() {\r\n" + 
				"            f2();\r\n" + 
				"            document.getElementById(\"starttime\").innerHTML = \"You started your Exam at : \" + f.getHours() + \":\" + f.getMinutes();\r\n" + 
				"            \r\n" + 
				"        }\r\n" + 
				"        function f2() {\r\n" + 
				"            if (parseInt(sec) > 0) {\r\n" + 
				"                sec = parseInt(sec) - 1;\r\n" + 
				"                document.getElementById(\"showtime\").innerHTML = \"Your Left Time  is : \"+min+\" Minutes ,\" + sec+\" Seconds\";\r\n" + 
				"                tim = setTimeout(\"f2()\", 1000);\r\n" + 
				"            }\r\n" + 
				"            else {\r\n" + 
				"                if (parseInt(sec) == 0) {\r\n" + 
				"                    \r\n" + 
				"                    if (parseInt(min) == 0) \r\n" + 
				"                    {\r\n" + 
				"                        clearTimeout(tim);\r\n" + 
				"                     //   location.href = \"index.jsp\";\r\n" + 
				"                        alert(\"Quiz Time Out click on Submit Button : \");\r\n" + 
				"                       \r\n" + 
				"                     document.getElementById(\"myform\").submit();\r\n" + 
				"                        \r\n" + 
				"                    }\r\n" + 
				"                    else \r\n" + 
				"                    {\r\n" + 
				"                        sec = 59;\r\n" + 
				"                        min = parseInt(min) - 1;\r\n" + 
				"                        document.getElementById(\"showtime\").innerHTML = \"Your Left Time  is : \" + min + \" Minutes ,\" + sec + \" Seconds\";\r\n" + 
				"                        tim = setTimeout(\"f2()\", 1000); \r\n" + 
				"                    }\r\n" + 
				"                    \r\n" + 
				"                }\r\n" + 
				"               \r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"    </script>");
		
		
		
		
		//window.location='/Online_MCQ_Portal/calculateMarks';
	
		pw.println("<div class='container'>"
						+ "<div class='row mt-5'>"
							+ "<div class='col-md-8 col-md-offset-2'> <form action='calculateMarks' method='post' id='myform'>"
						);
		
		
		
		pw.println("<div class='jumbotron bg-light'>	"
						+ "<h4><div id='starttime' class='text-success'>"
						+ "</div></h4>"
						+ "<h4><div id='showtime' class='text-danger'>"
						+ "  </div></h4>"
						+ "<h5><div class='text-danger'>"+"Note : Don't refresh, the Quiz will get suspended"
						+ "  </div></h5>"
						+ "<h5><div class='text-danger'>"+"Note : Don't close the window, you will not able to give quiz again"
						+ "  </div></h5>"
						+ "<h5><div class='text-info'>"+"Number of Questions : "+pegi
						+ "  </div></h5>"
						+ "<h5><div class='text-info'>"+"Total Marks : "+marks
						+ "  </div></h5>"
						
				   + "</div>");
			
		
		
		ResultSet rs;
		try
		{ 
			int i=1;
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			 
			 
			 PreparedStatement ps1 = con.prepareStatement("select * from student where name='"+ session.getAttribute("student") +"' ");
			 ResultSet rs1 = ps1.executeQuery();
			 
			 rs1.next();
			 
			 if(rs1.getString(3).equals("1"))
			 {
				 PreparedStatement ps3 = con.prepareStatement("update student set attempt='0' where name='"+ session.getAttribute("student") +"' ");
				 ps3.executeUpdate();
			 }
			 else if(rs1.getString(3).equals("0"))
			 {
				 pw.println(" <script>\r\n" + 
				 		"				 	alert('Quiz is not Available, You have already attempted quiz !');\r\n" + 
				 		"				 	 window.close(); window.location='QuizReport'; \r\n" + 
				 		"				 </script>");
				
			 }
			 
			 int divno=1;
			 
			 int total=0;
			 PreparedStatement ps4 = con.prepareStatement("select * from questions");
			 ResultSet rs4 =  ps4.executeQuery();
			 while(rs4.next())
				 total++;
			 
			 PreparedStatement ps = con.prepareStatement("select * from questions");
			 rs =  ps.executeQuery();
			 while(rs.next())
			 {
				 if(i%5==1)
				 {
					 pw.println("<div class='page' id='page"+divno+"'>");
					 System.out.println("open : "+i+" page no : "+divno);
				 }
				 
				 
				pw.println("<div class='jumbotron mt-3 mb-3 bg-light'>");
				
				pw.println("<h5><div class='text-dark'>"+i+".  "+rs.getString(3)+"</div></h5>"
						+"<div class='radio mt-2'><label><input type='radio' name="+rs.getString(1)+" value='a'"+">&nbsp;&nbsp;&nbsp;"+rs.getString(4)+"</label></div>"
						+"<div class='radio mt-2'><label><input type='radio' name="+rs.getString(1)+" value='b'"+">&nbsp;&nbsp;&nbsp;"+rs.getString(5)+"</label></div>"
						+"<div class='radio mt-2'><label><input type='radio' name="+rs.getString(1)+" value='c'"+">&nbsp;&nbsp;&nbsp;"+rs.getString(6)+"</label></div>"
						+"<div class='radio mt-2'><label><input type='radio' name="+rs.getString(1)+" value='d'"+">&nbsp;&nbsp;&nbsp;"+rs.getString(7)+"</label></div>"
	
						);
			
				pw.println("</div>");

				 if(i%5==0)
				 {
					 System.out.println("close : "+i);
					 pw.println("</div>");
					 divno++;
				 }
				 if(i==total)
				 {
					 System.out.println("at the close : "+i);
					 pw.println("</div>");
				 }
				 i++;
			 }
			 con.close();
			 pw.println("<ul id=\"pagination-demo\" class=\"pagination-lg pull-right\"></ul>");
			 pw.println("<input type='submit' name='sumbit' onclick='noBack();' class='btn btn-danger form-control' value='Submit Quiz Now' style='height:40px; margin-bottom:50px; size:20px;'> </form> </div> </div> </div>");
			 
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}	
	}

}
