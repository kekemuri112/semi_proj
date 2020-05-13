package semi.contents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contents/insert.do")
public class Contents_insertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cafe_num=Integer.parseInt(req.getParameter("cafe_num"));
		int notice_num=Integer.parseInt(req.getParameter("notice_num"));
		req.setAttribute("cafe_num", cafe_num);
		req.setAttribute("notice_num", notice_num);
		req.setAttribute("file", "/contents/contents_write.jsp");
		req.getRequestDispatcher("/semi/home.do").forward(req, resp);	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int notice_num=Integer.parseInt(req.getParameter("notice_num"));
		int num=(int)req.getSession().getAttribute("users_num");
		ContentsDao dao=ContentsDao.getDao();
		String contents_title=req.getParameter("title");
		String contents_post=req.getParameter("post");
		ContentsVo vo=new ContentsVo(0, notice_num, num, contents_title, contents_post, null, null);
		int n=dao.insert(vo);
		int cafe_num=dao.getCafe_Num(notice_num);
		System.out.println("insert ������ : "+n);
		if(n>0) {
			System.out.println("���༺��!!");

		}else {
			System.out.println("�������!!");
		}
		req.setAttribute("notice_num", notice_num);
		req.setAttribute("cafe_num", cafe_num);
		req.getRequestDispatcher("/semi/contents.do");
	}
}
