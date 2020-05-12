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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int notice_num=Integer.parseInt(req.getParameter("notice_num"));
		int num=(int)req.getSession().getAttribute("users_num");
		String id=(String)req.getSession().getAttribute("users_id");
		ContentsDao dao=ContentsDao.getDao();
		String contents_title=req.getParameter("title");
		String contents_post=req.getParameter("post");
		ContentsVo vo=new ContentsVo(0, notice_num, num, contents_title, contents_post, null, null);
		dao.insert(vo);
		
				
	}
}
