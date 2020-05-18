package semi.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/reg/updatacontroller.do")
public class UpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String users_id=req.getParameter("users_id");
		UsersVo vo=UsersDao.getInstance().information(users_id);
		req.setAttribute("vo", vo);
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("mlist", "/cafe/cafelist.do");
		req.setAttribute("mfile", "/contents/cmain.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
	}
	
}
