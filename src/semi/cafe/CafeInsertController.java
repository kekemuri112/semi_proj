package semi.cafe;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.notice.NoticeDao;


@WebServlet("/cafe/cafeinsert.do")
public class CafeInsertController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("cafelist", CafeDao.getInstance().listAll());
		req.setAttribute("mlist", "/cafe/cafelist.jsp");
		req.setAttribute("mfile", "/cafe/cafeInsert.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String cafe_name=req.getParameter("cafe_name");
		String cafe_desc=req.getParameter("cafe_desc");
		String cafe_intent=req.getParameter("cafe_intent");
		String cafe_admin=(String)req.getSession().getAttribute("users_id");
		String scafe_image=req.getParameter("cafe_image");
		String cafe_image=null;
		if(scafe_image!=null) {
			cafe_image=scafe_image;
		}
		CafeVo vo=new CafeVo(0,cafe_name,cafe_desc,cafe_intent,cafe_admin,null,cafe_image);
		CafeDao dao=CafeDao.getInstance();
		int n=dao.insert(vo);
		if(n>0) {
			req.setAttribute("msg", "개설요청완료");
		}else {
			req.setAttribute("msg", "요청실패");
		}
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("cafelist", CafeDao.getInstance().listAll());
		req.setAttribute("mlist", "/cafe/cafelist.jsp");
		req.setAttribute("mfile", "/home/result.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
	
}
