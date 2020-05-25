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
		boolean bl=false;
		session.setAttribute("cafe_admin",bl);
		session.setAttribute("userscafe",bl);
		session.setAttribute("userscafeApproved",bl);
		System.out.println(session.getAttribute("cafe_admin"));
		req.setAttribute("msg", "로그아웃완료!");
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mfile", "/home/result.jsp");
		int cafe_num= (Integer)session.getAttribute("cafe_num");
		if(cafe_num>0) {
			req.setAttribute("mlist", "/notice/noticelist.jsp");
		}else {
			req.setAttribute("cafelist", CafeDao.getInstance().listAll());
			req.setAttribute("mlist", "/cafe/cafelist.jsp");
		}
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
