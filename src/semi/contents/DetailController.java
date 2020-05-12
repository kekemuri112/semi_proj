package semi.contents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/semi/detail.do")
public class DetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int contents_num=Integer.parseInt(req.getParameter("contents_num"));
		ContentsDao dao=ContentsDao.getDao();
		Contents_detailVo vo=dao.detail(contents_num);
		req.setAttribute("vo", vo);
		req.setAttribute("cp",req.getContextPath());
		req.getRequestDispatcher("/contents/detail.jsp").forward(req, resp);
	}
}
