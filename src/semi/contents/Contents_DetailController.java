package semi.contents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.cafe.CafeDao;

@WebServlet("/contents/detail.do")
public class Contents_DetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String scontents_num=req.getParameter("contents_num");
		if(scontents_num==null) {
			scontents_num=(String)req.getAttribute("contents_num");
		}
		int contents_num=Integer.parseInt(scontents_num);
		ContentsDaoBackup dao=ContentsDaoBackup.getDao();
		Contents_detailVo vo=dao.detail(contents_num);
		req.setAttribute("vo", vo);
		req.setAttribute("noticelist", req.getAttribute("noticelist"));
		req.setAttribute("cafelist", CafeDao.getInstance().listAll());
		req.setAttribute("header1", req.getAttribute("header1"));
		req.setAttribute("header2", req.getAttribute("header2"));
		req.setAttribute("headerLog", req.getAttribute("headerLog"));
		req.setAttribute("mlist", req.getAttribute("mlist"));
		req.setAttribute("mfile","/contents/detail.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
