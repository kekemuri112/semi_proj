package semi.contents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.contents.ContentsDao;

@WebServlet("/contents/delete.do")
public class Contents_DeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String scontents_num=req.getParameter("contents_num");
		int contents_num=Integer.parseInt(scontents_num);
		ContentsDao dao=ContentsDao.getDao();
		int n=dao.delete(contents_num);
		if(n>0) {
			req.setAttribute("mlist", "/notice/noticelist.jsp");
			req.setAttribute("mfile","/contents/contents.jsp" );
		}else {
			req.setAttribute("msg", "삭제가 안됐습니다.");
			req.setAttribute("mfile","/home/result.jsp" );
			
		}
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
		
	}
}
