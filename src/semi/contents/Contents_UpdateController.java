package semi.contents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.cafe.CafeDao;
@WebServlet("/contents/update.do")
public class Contents_UpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int contents_num=Integer.parseInt(req.getParameter("contents_num"));
		ContentsDao dao=ContentsDao.getDao();
		Contents_detailVo vo=dao.detail(contents_num);
		req.setAttribute("vo", vo);
		String contents_post=vo.getContents_post();
		contents_post=contents_post.replaceAll("<br>", "\r\n");
		vo.setContents_post(contents_post);
		req.setAttribute("vo", vo);
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mlist", "/notice/noticelist.jsp");
		req.setAttribute("mfile","/contents/contents_update.jsp" );
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String scontents_num=req.getParameter("contents_num");
		int contents_num=Integer.parseInt(scontents_num);
		String contents_title=req.getParameter("contents_title");
		String contents_post=req.getParameter("contents_post");
		contents_post=contents_post.replaceAll("\r\n", "<br>");
		ContentsDao dao=ContentsDao.getDao();
		int n=dao.update_point(contents_title, contents_post, contents_num);
		req.setAttribute("contents_num", contents_num);
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mlist", "/notice/noticelist.jsp");
		Contents_detailVo vo=dao.detail(contents_num);
		req.setAttribute("vo", vo);
		req.setAttribute("mfile","/contents/detail.jsp" );
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}

