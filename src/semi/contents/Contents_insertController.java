package semi.contents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/contents/insert.do")
public class Contents_insertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		int cafe_num=Integer.parseInt(req.getParameter("cafe_num"));
		int notice_num=Integer.parseInt(req.getParameter("notice_num"));
		req.setAttribute("cafe_num", cafe_num);
		req.setAttribute("notice_num", notice_num);
		req.setAttribute("mfile", "/contents/contents_write.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		int notice_num=Integer.parseInt(req.getParameter("notice_num"));
		int users_num=(int)req.getSession().getAttribute("users_num");
		ContentsDao dao=ContentsDao.getDao();
		String contents_title=req.getParameter("title");
		String contents_post=req.getParameter("post");
		ContentsVo vo=new ContentsVo(0, notice_num, users_num, contents_title, contents_post, null, null);
		int n=dao.insert(vo);
		if(n>0) {
			dao.updatePoint(users_num);
		}
		int cafe_num=dao.getCafe_Num(notice_num);

		req.setAttribute("notice_num", notice_num);
		req.setAttribute("cafe_num", cafe_num);
		req.setAttribute("mfile", "/contents/contents.do");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);;
	}
}
