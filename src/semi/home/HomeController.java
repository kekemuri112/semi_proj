package semi.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/semi/home.do")
public class HomeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp=req.getContextPath();
		req.getServletContext().setAttribute("cp", cp);
		String file=req.getParameter("file");
		if(file==null) {
			file=(String)req.getAttribute("file");
		}
		req.setAttribute("file", file);
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	
	}
}
