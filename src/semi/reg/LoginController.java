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
@WebServlet("/reg/logincontroller.do")
public class LoginController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String users_id=req.getParameter("users_id");
		String users_pwd=req.getParameter("users_pwd");
		UsersDao dao= UsersDao.getInstance();
		int n=dao.loginOk(users_id, users_pwd);
		HttpSession session=req.getSession();
		if(n>0) {
			UsersVo vo=dao.information(users_id);
			session.setAttribute("users_id", users_id);
			session.setAttribute("users_num", vo.getUsers_num());
			req.setAttribute("msg", "로그인 완료되었습니다.");
		}else {
			req.setAttribute("msg", "로그인 실패하였습니다.");
		}
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mfile", "/home/result.jsp");
		String cafe_num=req.getParameter("cafe_num");
		if(cafe_num!=null&&!cafe_num.equals("")) {
			String snotice_num=req.getParameter("notice_num");
			int notice_num=Integer.parseInt(snotice_num);
			req.setAttribute("noticelist", NoticeDao.getInstance().listAll(notice_num));
			req.setAttribute("mlist", "notice/noticelist.jsp");
		}else {
			req.setAttribute("cafelist", CafeDao.getInstance().listAll());
			req.setAttribute("mlist", "/cafe/cafelist.jsp");
		}
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
