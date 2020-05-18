package semi.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.cafe.CafeDao;
@WebServlet("/reg/pwdsearch.do")
public class PwdsearchController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		HttpSession session=req.getSession();
		session.setAttribute("mfile", "/register/search.jsp?idsearch=2");
		req.setAttribute("cafelist", CafeDao.getInstance().listAll());
		
		req.setAttribute("idsearch", 2);
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String users_id=req.getParameter("users_id");
		String users_name=req.getParameter("users_name");
		String users_email=req.getParameter("users_email");
		String pwd=UsersDao.getInstance().search(users_id, users_name, users_email);
		HttpSession session=req.getSession();
		if(pwd==null ) {
			session.setAttribute("msg", "맞는 정보가 없습니다 다시 한번 확인 바랍니다.");
		}else {
			String[] pwds=pwd.split("");
			String users_pwd="";
			for(int i=0;i<pwds.length;i++) {
				if(i>3 && i<6) {
					users_pwd+="*";
				}else {
					users_pwd+=pwds[i];
				}
			}
			session.setAttribute("msg", users_pwd+"비밀번호는 입니다.");
		}
		req.setAttribute("cafelist", CafeDao.getInstance().listAll());
		session.setAttribute("mfile", "/home/result.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
