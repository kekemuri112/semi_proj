package semi.cafe;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cafe/usersdel.do")
public class CafeDelete extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		HttpSession session=req.getSession();
		int users_num=(int)session.getAttribute("users_num");
		int cafe_num=(int)session.getAttribute("cafe_num");
		CafeDao dao=CafeDao.getInstance();
		int n=dao.usersdelete(cafe_num, users_num);
		if(n>0) {
			req.setAttribute("msg", "카페 탈퇴완료..");
		}else {
			req.setAttribute("msg", "카페 탈퇴실패..");
		}
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mlist", "/notice/noticelist.jsp");
		req.setAttribute("mfile", "/home/result.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
		
	}
}
