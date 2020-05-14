package semi.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/notice/noticelist.do")
public class NoticeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		NoticeDao dao=NoticeDao.getInstance();
		HttpSession session= req.getSession();
		int cafe_num=Integer.parseInt(req.getParameter("cafe_num"));
		String snum=req.getParameter("notice_num");
		//카페번호가 눌렸을때
		if(snum==null || snum.equals("")) {
			ArrayList<NoticeVo> noticelist=dao.listAll(cafe_num);
			session.setAttribute("cafe_name", req.getParameter("cafe_name"));
			session.setAttribute("noticelist", noticelist);
			session.setAttribute("cafe_num", cafe_num);
			session.setAttribute("notice_num", req.getAttribute("notice_num"));
			session.setAttribute("header2", "/home/wrapmain.jsp");
			session.setAttribute("mlist", "/notice/noticelist.jsp");
			session.setAttribute("mfile", "/contents/contents.do");
			req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
		}else {
			// 게시글 버튼 눌렀을때
			int notice_num=Integer.parseInt(snum);
			NoticeVo vo=dao.getVo(notice_num);
			req.setAttribute("vo",vo );
			session.setAttribute("notice_num", notice_num);
			session.setAttribute("mfile", "/contents/contents.do");
			req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
	
	}
}
