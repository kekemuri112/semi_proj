package semi.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/reg/logincontroller.do")
public class LoginController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String users_id=req.getParameter("users_id");
		String users_pwd=req.getParameter("users_pwd");
		int n=UsersDao.getInstance().loginOk(users_id, users_pwd);
		if(n>0) {
			req.setAttribute("msg", "로그인 완료되었습니다.");
		}else {
			req.setAttribute("msg", "로그인 실패하였습니다.");
		}
		HttpSession session=req.getSession();
		session.setAttribute("headerLog", "/register/rmain.jsp");
		session.setAttribute("header2", "/home/wrapmain.jsp");
		session.setAttribute("mlist", "/cafe/cafelist.do");
		session.setAttribute("mfile", "/contents/cmain.jsp");
		req.getRequestDispatcher("").forward(req, resp);
	}
}
