package com.fauxcode;

import java.io.File;
import java.io.FileOutputStream;
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

/*import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
*/
import java.io.File; 
import java.util.Date; 

/**
 * Servlet implementation class ReportAllStdents
 */
public class ReportAllStdents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportAllStdents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ReportAllStdents
	/*	HttpSession session = request.getSession(false);
		
		try
		{
			if(session!=null)
			{
				if(session.getAttribute("admin").equals(""))
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
		}*/
		
		
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "inline; filename='abc.xls'");
		try
		{
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			 
			 PreparedStatement ps = con.prepareStatement("select * from student");
			 ResultSet rs =  ps.executeQuery();
			 
			 pw.println("<table border='1' cellpadding='3' bordercolor='black'> <tr> <th> Roll Number </th> <th> Marks Obtained </th> <th> Total Marks </th> </tr>");
			 
			 while(rs.next())
			 {
				 if(rs.getInt(4)!=-1)
				 {
					 pw.println("<tr>");
					 pw.println("<td>"+rs.getString(1)+"</td>");
					 pw.println("<td>"+rs.getString(4)+"</td>");
					 pw.println("<td>"+rs.getString(5)+"</td>");
					 pw.println("</tr>");
				 }
				 else if(rs.getInt(3)==0 && rs.getInt(4)==-1)
				 {
					 pw.println("<tr>");
					 pw.println("<td>"+rs.getString(5)+"</td>");
					 pw.println("<td>0</td>");
					 pw.println("<td>"+rs.getString(5)+"</td>");
					 pw.println("</tr>");
				 } 
				 else
				 {
					 pw.println("<tr>");
					 pw.println("<td>"+rs.getString(1)+"</td>");
					 pw.println("<td>0</td>");
					 pw.println("<td>0</td>");
					 pw.println("</tr>");
				 }
			 }
			 pw.println("</table>");
			 con.close();
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
