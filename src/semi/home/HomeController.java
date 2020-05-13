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
			file="";
			//file=(String)req.getAttribute("file");
		}
		String mlist=req.getParameter("mlist");
		if(mlist==null) {
			mlist="/cafe/cafelist.do";
			//mlist=(String)req.getAttribute("mlist");
		}
		
		req.setAttribute("file", file);
		req.setAttribute("mlist", mlist);
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	
	}
}
