package semi.cafe;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cafe/cafelist.do")
public class CafeListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		CafeDao dao=CafeDao.getInstance();
		ArrayList<CafeVo> cafelist=dao.listAll();	
		HttpSession session= req.getSession();
		session.setAttribute("cafelist", cafelist);
		session.setAttribute("mlist", "/cafe/cafelist.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
