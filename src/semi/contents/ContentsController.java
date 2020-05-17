package semi.contents;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.cafe.CafeDao;

@WebServlet("/contents/contents.do")
public class ContentsController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String scafe_num=req.getParameter("cafe_num");
		int cafe_num=0;
		if(scafe_num!=null) {
			cafe_num=Integer.parseInt(scafe_num);
		}
		String spageNum=req.getParameter("pageNum");
		String snotice_num=req.getParameter("notice_num");
		int notice_num=0;
		String notice_name=null;
		ContentsDao dao=ContentsDao.getDao();
		if(snotice_num!=null) {
			notice_num=Integer.parseInt(snotice_num);
			notice_name=dao.getNotice_name(notice_num);
		}
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		ArrayList<Contents_ListVo> list=dao.listAll(cafe_num, startRow, endRow,notice_num);
		int pageCount=(int)Math.ceil(dao.getCount(cafe_num,notice_num)/10.0);
		int startPage=((pageNum-1)/5)*5+1;
		int endPage=startPage+4;
		if(endPage>pageCount){
			endPage=pageCount;
		}
		HttpSession session=req.getSession();
		session.setAttribute("notice_num", notice_num);
		session.setAttribute("notice_name", notice_name);
		session.setAttribute("cafe_num", cafe_num);
		session.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum",pageNum);
		session.setAttribute("mfile","/contents/content.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
