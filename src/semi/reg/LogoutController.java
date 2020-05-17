package semi.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.cafe.CafeDao;

@WebServlet("/reg/logoutcontroller.do")
public class LogoutController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		HttpSession session=req.getSession();
		session.removeAttribute("users_id");
		session.removeAttribute("users_num");
		req.setAttribute("msg", "로그아웃 하였습니다.");
		req.setAttribute("cafelist", CafeDao.getInstance().listAll());
		session.setAttribute("headerLog", "/register/rmain.jsp");
		session.setAttribute("mfile", "/home/result.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
