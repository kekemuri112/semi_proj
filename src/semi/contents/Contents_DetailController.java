package semi.contents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/contents/detail.do")
public class Contents_DetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String scontents_num=req.getParameter("contents_num");
		int contents_num=Integer.parseInt(scontents_num);
		ContentsDao dao=ContentsDao.getDao();
		Contents_detailVo vo=dao.detail(contents_num);
		req.setAttribute("vo", vo);
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mlist", "/notice/noticelist.jsp");
		req.setAttribute("mfile", "/contents/detail.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
		// 변경

	}
}
