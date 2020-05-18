package semi.questions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/questions/subscontroller.do")
public class SubsController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String scafe_num=req.getParameter("cafe_num");
		String cafereg_question=req.getParameter("cafereg_question");
		int cafe_num=0;
		if(scafe_num!=null) {
			cafe_num=Integer.parseInt(scafe_num);
		}
		int n=CaferegDao.getInstance().insert(cafe_num, cafereg_question);
		if(n>0) {
			req.setAttribute("msg", "질문저장완료");
		}else {
			req.setAttribute("msg", "질문저장실패");
		}
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("mlist", "/cafe/cafelist.do");
		req.setAttribute("mfile", "/contents/cmain.jsp");
		req.getRequestDispatcher("/questions/subscription.jsp").forward(req, resp);
	}
}
