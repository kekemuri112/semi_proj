package semi.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.cafe.CafeDao;

@WebServlet("/semi/home.do")
public class HomeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String cp=req.getContextPath();
		HttpSession session= req.getSession();
		session.setAttribute("cafe_num", 0);
		session.setAttribute("cafe_admin", false);
		session.setAttribute("userscafe", false);
		session.setAttribute("userscafeApproved", false);
		session.removeAttribute("cafe_name");
		req.getServletContext().setAttribute("cp", cp);
		req.setAttribute("cafelist", CafeDao.getInstance().listAll());
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mlist", "/cafe/cafelist.jsp");
		req.setAttribute("mfile", "/contents/cmain.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
