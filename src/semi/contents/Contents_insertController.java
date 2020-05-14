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
		System.out.println("cafe_num : "+cafe_num);
		System.out.println("notice_num : "+notice_num);
		req.setAttribute("cafe_num", cafe_num);
		req.setAttribute("notice_num", notice_num);
		req.setAttribute("file", "/contents/contents_write.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int notice_num=Integer.parseInt(req.getParameter("notice_num"));
		int users_num=(int)req.getSession().getAttribute("users_num");
		ContentsDao dao=ContentsDao.getDao();
		String contents_title=req.getParameter("contents_title");
		String contents_post=req.getParameter("contents_post");
		ContentsVo vo=new ContentsVo(0, notice_num, users_num, contents_title, contents_post, null, null);
		int n=dao.insert(vo);
		if(n>0) {
			dao.updatePoint(users_num);
		}
		int cafe_num=dao.getCafe_Num(notice_num);
		System.out.println("cafe_num : "+cafe_num);
		System.out.println("insert 실행결과 : "+n);
		if(n>0) {
			System.out.println("실행성공!!");
		}else {
			System.out.println("실행실패!!");
		}
		req.setAttribute("notice_num", notice_num);
		req.setAttribute("cafe_num", cafe_num);
		req.getRequestDispatcher("/semi/contents.do").forward(req, resp);;
	}
}
