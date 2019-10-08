

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import sun.util.calendar.BaseCalendar.Date;

/**
 * Servlet implementation class QuizSetting
 */
public class QuizSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizSetting() {
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
					response.sendRedirect("quiz_setting.html");
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
		
		try
		{
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));

			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			Class.forName("com.mysql.cj.jdbc.Driver");  
			PrintWriter pw = response.getWriter();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mcq?useSSL=false","root","");
			
			PreparedStatement ps = con.prepareStatement("update quizsetting set date=?,time=?,password=? where id='1'");
			ps.setDate(1,sqlDate);
			ps.setString(2,request.getParameter("time"));
			ps.setString(3,request.getParameter("password"));
			
			
			int result = ps.executeUpdate();
			
			PreparedStatement ps2 = con.prepareStatement("update student set attempt='1',marks='-1',total='-1'");
			ps2.executeUpdate();
			
			if(result>0)
			{
				con.close();
				pw.println("<script> alert('Quiz Setting Updated'); window.location='admin_dashboard.html' </script>");
			}
			
		}
		catch(Exception e)
		{
			
		}
		
	}

}
