

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutAdmin
 */
public class LogoutAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false); 
		
		if(session==null)
		{
			//response.sendRedirect("index.html");
		}
		else
		{
			session.removeAttribute("admin");
		//	response.sendRedirect("index.html");
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
				"    localStorage.admin = 'no';\r\n" + 
				"window.location='index.html'"+
				"} \r\n" + 
				"else\r\n" + 
				"{\r\n" + 
				"   alert('Sorry, your browser does not support Web Storage...');\r\n" + 
				"}\r\n" + 
				"</script>");
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
