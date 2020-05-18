package semi.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.cafe.CafeDao;
import semi.notice.NoticeDao;

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
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mfile", "/home/result.jsp");
		String cafe_num=req.getParameter("cafe_num");
		if(cafe_num!=null&&!cafe_num.equals("")) {
			String snotice_num=req.getParameter("notice_num");
			int notice_num=Integer.parseInt(snotice_num);
			req.setAttribute("noticelist", NoticeDao.getInstance().listAll(notice_num));
			req.setAttribute("mlist", "notice/noteiclist.jsp");
		}else {
			req.setAttribute("cafelist", CafeDao.getInstance().listAll());
			req.setAttribute("mlist", "/cafe/cafelist.jsp");
		}
		
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
