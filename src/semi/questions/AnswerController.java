package semi.questions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.notice.NoticeDao;

//카페 가입 버튼 눌렀을때...컨트롤러
@WebServlet("/questions/answer.do")
public class AnswerController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String[] contents=req.getParameterValues("answer");
		String[] scafereg_num=req.getParameterValues("cafereg_num");
		String scafe_num=(String)req.getParameter("cafe_num");
		HttpSession session=req.getSession();
		int users_num=(int)session.getAttribute("users_num");
		int cafe_num=0;
		if(scafereg_num!=null) {
			cafe_num=Integer.parseInt(scafe_num);
		}
		int n=CaferegDao.getInstance().usersCafeInsert(contents, scafereg_num, cafe_num, users_num);
		if(n>0) {
			req.setAttribute("msg", "가입요청성공");
		}else {
			req.setAttribute("msg", "가입요청실패");
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
