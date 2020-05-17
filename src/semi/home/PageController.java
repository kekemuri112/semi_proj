package semi.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.cafe.CafeDao;
@WebServlet("/semi/pagecontroller.do")
public class PageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		int check=Integer.parseInt(req.getParameter("check"));
		req.setAttribute("cafelist", CafeDao.getInstance().listAll());
		HttpSession session= req.getSession();
		String[] checks= {"/register/login.jsp","/register/register.jsp","/register/regsearch.jsp",
				"/reg/logoutcontroller.do","","","/cafe/cafeapproval.jsp",""};
		
		switch(check) {
			//로그인
			case 1:	session.setAttribute("mfile", checks[check-1]);
				break;
			case 2:	session.setAttribute("mfile", checks[check-1]);
				break;
			case 3:	session.setAttribute("mfile", checks[check-1]);
				break;
			case 4:	session.setAttribute("mfile", checks[check-1]);
				break;
			case 5:	session.setAttribute("mfile", checks[check-1]);
				break;
			case 6:	session.setAttribute("mfile", checks[check-1]);
				break;
			case 7:	session.setAttribute("mfile", checks[check-1]);
				break;
		}
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
