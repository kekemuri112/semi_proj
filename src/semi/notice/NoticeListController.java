package semi.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/notice/noticelist.do")
public class NoticeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeDao dao=NoticeDao.getInstance();
		int cafe_num=Integer.parseInt(req.getParameter("cafe_num"));
		String snum=req.getParameter("notice_num");
		if(snum==null) {
			ArrayList<NoticeVo> noticelist=dao.listAll(cafe_num);
			req.setAttribute("noticelist", noticelist);
			req.getRequestDispatcher("/notice/noticeList.jsp").forward(req, resp);
		}else {
			int notice_num=Integer.parseInt(snum);
			NoticeVo vo=dao.getVo(notice_num);
			req.setAttribute("vo",vo );
			req.getRequestDispatcher("/notice/noticeAdd.jsp").forward(req, resp);
		}
	}
}
