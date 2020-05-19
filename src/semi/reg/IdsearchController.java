package semi.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.cafe.CafeDao;
@WebServlet("/reg/idsearch.do")
public class IdsearchController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		req.setAttribute("idsearch", 1);
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		int cafe_num=(int)req.getSession().getAttribute("cafe_num");
		if(cafe_num!=0 ) {
			req.setAttribute("mlist", "/notice/noticelist.jsp");
		}else {
			req.setAttribute("cafelist", CafeDao.getInstance().listAll());
			req.setAttribute("mlist", "/cafe/cafelist.jsp");
		}
		req.setAttribute("mfile", "/register/search.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String users_name=req.getParameter("users_name");
		String users_email=req.getParameter("users_email");
		String id=UsersDao.getInstance().search(null, users_name, users_email);
		if(id==null) {
			req.setAttribute("msg", "정보를 잘 못 입력하였습니다.");
		}else {
			String[] ids=id.split("");
			String users_id="";
			for(int i=0;i<ids.length;i++) {
				if(i>3 && i<6) {
					users_id+="*";
				}else {
					users_id+=ids[i];
				}
			}
			req.setAttribute("msg", users_id+"입니다.");
		}
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		int cafe_num=(int)req.getSession().getAttribute("cafe_num");
		if(cafe_num!=0 ) {
			req.setAttribute("mlist", "/notice/noticelist.jsp");
		}else {
			req.setAttribute("cafelist", CafeDao.getInstance().listAll());
			req.setAttribute("mlist", "/cafe/cafelist.jsp");
		}
		req.setAttribute("mfile", "/home/result.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
