

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudent() {
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
				if(session.getAttribute("admin").equals(""))
				{
					response.sendRedirect("index.html");
				}
				else
				{
					response.sendRedirect("addstudent.html");
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
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String nm = request.getParameter("name");
		String pass = request.getParameter("password");
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			PrintWriter pw = response.getWriter();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			
			PreparedStatement ps1 = con.prepareStatement("select * from student where name=? ");
			ps1.setString(1,nm);
			
			ResultSet rs = ps1.executeQuery();
			
			if(rs.next())
			{
				con.close();
				pw.println("<script> alert('Data with roll number alredy exists'); window.location='addstudent.html' </script>");
			}
			else
			{
				PreparedStatement ps = con.prepareStatement("insert into student values(?,?,?,?,?)");
				ps.setString(1,nm);
				ps.setString(2,pass);
				ps.setString(3,"1");
				ps.setString(4,"-1");
				ps.setString(5,"-1");
				
				int result = ps.executeUpdate();
				if(result>0)
				{
					con.close();
					pw.println("<script> alert('Data Inserted'); window.location='admin_dashboard.html' </script>");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			response.getWriter().println("<script>alert('student already exists !'); </script>");
		}
	}

}
