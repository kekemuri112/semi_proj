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
import semi.notice.NoticeDao;

@WebServlet("/contents/contents.do")
public class ContentsController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String scafe_num=req.getParameter("cafe_num");
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		int cafe_num=0;
		if(scafe_num!=null) {
			cafe_num=Integer.parseInt(scafe_num);
		}
		String spageNum=req.getParameter("pageNum");
		String snotice_num=req.getParameter("notice_num");
		System.out.println(snotice_num);
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
		ArrayList<Integer> list2=dao.getNotice_num(cafe_num);
		if(list2!=null) {
			ArrayList<Contents_ListVo> list=dao.listAll(cafe_num, startRow, endRow, notice_num, field, keyword);
			int pageCount=(int)Math.ceil(dao.getCount(cafe_num,notice_num, field, keyword)/10.0);
			int startPage=((pageNum-1)/5)*5+1;
			int endPage=startPage+4;
			if(endPage>pageCount){
				endPage=pageCount;
			}
			req.setAttribute("notice_num", notice_num);
			req.setAttribute("notice_name", notice_name);
			req.setAttribute("cafe_num", cafe_num);
			req.setAttribute("list", list);
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageNum",pageNum);
			req.setAttribute("mfile","/contents/content.jsp");
		}else {
			req.setAttribute("msg","게시글이 없습니다.");
			req.setAttribute("mfile","/home/result.jsp");
		}
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		
		HttpSession session= req.getSession();
		NoticeDao ndao=NoticeDao.getInstance();
		String users_id=(String)session.getAttribute("users_id");
		String bl="false";
		String bl2="false";
		if(users_id!=null) {
			bl=ndao.cafeAdmin(users_id,cafe_num);
			int users_num=(int)session.getAttribute("users_num");
			bl2=ndao.usersCafe(users_num, cafe_num);
		}
		session.setAttribute("cafe_admin", bl);
		session.setAttribute("userscafe", bl2);
		if(cafe_num!=0&&notice_num!=0) {
			req.setAttribute("noticelist", NoticeDao.getInstance().listAll(notice_num));
			req.setAttribute("mlist", "/notice/noteiclist.jsp");
		}else {
			req.setAttribute("cafelist", CafeDao.getInstance().listAll());
			req.setAttribute("mlist", "/cafe/cafelist.jsp");
		}
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
