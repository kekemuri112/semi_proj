package semi.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/reg/searchcontroller.do")
public class PwdsearchController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String users_id=req.getParameter("users_id");
		String users_name=req.getParameter("users_name");
		String users_email=req.getParameter("users_email");
		String pwd=UsersDao.getInstance().search(users_id, users_name, users_email);
		String[] pwds=pwd.split("");
		String users_pwd="";
		for(int i=0;i<pwds.length;i++) {
			if(i>3 && i<6) {
				users_pwd+="*";
			}else {
				users_pwd+=pwds[i];
			}
		}
		HttpSession session=req.getSession();
		session.setAttribute("headerLog", "/register/rmain.jsp");
		session.setAttribute("header2", "/home/wrapmain.jsp");
		session.setAttribute("mlist", "/cafe/cafelist.do");
		session.setAttribute("mfile", "/contents/cmain.jsp");
		req.getRequestDispatcher("").forward(req, resp);
	}
}
