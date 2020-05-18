package semi.home;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.cafe.CafeDao;
import semi.cafe.CafeVo;

@WebServlet("/semi/home.do")
public class HomeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String cp=req.getContextPath();
		req.getServletContext().setAttribute("cp", cp);
		HttpSession session= req.getSession();
		String cafe_name=(String)session.getAttribute("cafe_name");
		if(cafe_name!=null) {
			session.removeAttribute("cafe_name");
		}
		CafeDao dao=CafeDao.getInstance();
		ArrayList<CafeVo> cafelist=dao.listAll();	
		req.setAttribute("cafelist", cafelist);
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mlist", "/cafe/cafelist.jsp");
		req.setAttribute("mfile", "/contents/cmain.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
