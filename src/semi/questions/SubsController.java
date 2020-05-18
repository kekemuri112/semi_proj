package semi.questions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.notice.NoticeDao;

@WebServlet("/questions/subscontroller.do")
public class SubsController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String cafe_num=req.getParameter("cafe_num");
		System.out.println("get방싱:"+cafe_num);
		req.setAttribute("cafe_num", cafe_num);
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mlist", "/cafe/cafelist.jsp");
		req.setAttribute("mfile", "/questions/subscription.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String scafe_num=req.getParameter("cafe_num");
		String cafereg_question=req.getParameter("question");
		int cafe_num=0;
		if(scafe_num!=null) {
			cafe_num=Integer.parseInt(scafe_num);
		}
		System.out.println("post방싱:"+cafe_num);
		int n=CaferegDao.getInstance().insert(cafe_num, cafereg_question);
		if(n>0) {
			req.setAttribute("msg", "질문저장완료");
		}else {
			req.setAttribute("msg", "질문저장실패");
		}
		req.setAttribute("noticelist", NoticeDao.getInstance().listAll(cafe_num));
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mlist", "/notice/noticelist.jsp");
		req.setAttribute("mfile", "/home/result.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
