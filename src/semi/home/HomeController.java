package semi.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/semi/home.do")
public class HomeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String cp=req.getContextPath();
		req.getServletContext().setAttribute("cp", cp);
		HttpSession session= req.getSession();
		session.setAttribute("header1", "/home/wraphome.jsp");
		session.setAttribute("headerLog", "/register/rmain.jsp");
		session.setAttribute("header2", "/home/wrapmain.jsp");
		session.setAttribute("mlist", "/cafe/cafelist.do");
		session.setAttribute("mfile", "/contents/cmain.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);

	}
}
