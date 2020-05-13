package semi.contents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/contents/update.do")
public class Contents_UpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contents_title=req.getParameter("contents_title");
		String contents_post=req.getParameter("contents_post");
		String scontents_num=req.getParameter("contents_num");
		int contents_num=Integer.parseInt(scontents_num);
		ContentsDao dao=ContentsDao.getDao();
		int n=dao.update(contents_title,contents_post,contents_num);
		if(n>0) {
			req.setAttribute("contents_num",contents_num );
			req.getRequestDispatcher("/semi/detail.do").forward(req, resp);
		}else {
			req.setAttribute("msg", "수정실패!!!");
		}
		
	}
	
}
