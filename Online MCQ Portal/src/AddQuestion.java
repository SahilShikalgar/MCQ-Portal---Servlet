

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
 * Servlet implementation class AddQuestion
 */
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestion() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
					response.sendRedirect("addque.html");
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
		
		String no = request.getParameter("qno");
		String marks = request.getParameter("qmarks");
		String que =  request.getParameter("que");
		String a =  request.getParameter("a");
		String b =  request.getParameter("b");
		String c =  request.getParameter("c");
		String d =  request.getParameter("d");
		String ans =  request.getParameter("ans");
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			PrintWriter pw = response.getWriter();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			
			
			
			PreparedStatement ps1 = con.prepareStatement("select no from questions where no='"+ no +"'");
			
			ResultSet rs = ps1.executeQuery();
			
			if(rs.next())
			{
				con.close();
				pw.println("<script> alert('question with same number alredy exists'); window.location='addque.html' </script>");
			}
			else
			{
				PreparedStatement ps = con.prepareStatement("insert into questions values(?,?,?,?,?,?,?,?)");
				ps.setString(1,no);
				ps.setString(2,marks);
				ps.setString(3, que);
				ps.setString(4,a);
				ps.setString(5,b);
				ps.setString(6,c);
				ps.setString(7,d);
				ps.setString(8,ans);
				
				int result = ps.executeUpdate();
				if(result>0)
				{
					con.close();
					pw.println("<script> alert('Data Inserted'); window.location='addque.html' </script>");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}

}
