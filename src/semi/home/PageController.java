package semi.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/semi/pagecontroller.do")
public class PageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int check=Integer.parseInt(req.getParameter("check"));
		resp.setContentType("text/plain;charset=utf-8");
		HttpSession session= req.getSession();
		switch(check) {
			case 1:	session.setAttribute("mfile", "/register/register.jsp");
				break;
			case 2:	session.setAttribute("mfile", "/register/register.jsp");
				break;
			case 3:	session.setAttribute("mfile", "/register/register.jsp");
				break;
			case 4:	session.setAttribute("mfile", "/register/register.jsp");
				break;
			case 5:	session.setAttribute("mfile", "/register/register.jsp");
				break;
			case 6:	session.setAttribute("mfile", "/register/register.jsp");
				break;
		}
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
