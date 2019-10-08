

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
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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
					response.sendRedirect("change_password.html");
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

		HttpSession session = request.getSession(false);
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
		}
		
		
		PrintWriter pw = response.getWriter();
		
		try
		{
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			 
			 PreparedStatement ps = con.prepareStatement("select * from admin");
			 ResultSet rs = ps.executeQuery();
			 rs.next();
			 
			 if(rs.getString(2).equals(request.getParameter("old")))
			 {
				 PreparedStatement ps2 = con.prepareStatement("update admin set password='"+request.getParameter("new")+"' where name='admin'");
				 int res = ps2.executeUpdate();
				 if(res>0)
				 {
				 	 pw.println("<script>  alert(' Password Changed Successfully'); window.location='admin_dashboard.html' </script>");
				 }	
				 else
				 {
					 pw.println("<script>  alert(' Not Changed'); window.location='change_password.html' </script>");
				 }
			 }
			 else
			 {
				 pw.println("<script>  alert('Old password is not matching !'); window.location='change_password.html' </script>");
			 }
			 
			 
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}

}
