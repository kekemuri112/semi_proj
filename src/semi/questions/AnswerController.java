package semi.questions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//카페 가입 버튼 눌렀을때...컨트롤러
@WebServlet("/questions/answer.do")
public class AnswerController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String scafe_num=req.getParameter("cafe_num");
		int cafe_num=0;
		if(scafe_num!=null) {
			cafe_num=Integer.parseInt(scafe_num);
		}
		HttpSession session=req.getSession();
		String susers_num=(String)session.getAttribute("users_num");
		if(susers_num==null) {
			req.setAttribute("msg", "fail");
			req.getRequestDispatcher("").forward(req, resp);
		}
		ArrayList<String> list=CaferegDao.getInstance().getQuestions(cafe_num);
		req.setAttribute("list", list);
		session.setAttribute("headerLog", "/register/rmain.jsp");
		session.setAttribute("header2", "/home/wrapmain.jsp");
		session.setAttribute("mlist", "/cafe/cafelist.do");
		session.setAttribute("mfile", "/contents/cmain.jsp");
		req.getRequestDispatcher("/questions/answer.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String[] contents=req.getParameterValues("answer");
		String scafereg_num=req.getParameter("cafereg_num");
		String scafe_num=req.getParameter("cafe_num");
		HttpSession session=req.getSession();
		String susers_num=(String)session.getAttribute("users_num");
		int cafereg_num=0;
		int cafe_num=0;
		int users_num=0;
		if(scafereg_num!=null) {
			cafereg_num=Integer.parseInt(scafereg_num);
			cafe_num=Integer.parseInt(scafe_num);
			users_num=Integer.parseInt(susers_num);
		}
		int n=CaferegDao.getInstance().usersCafeInsert(contents, cafereg_num, cafe_num, users_num);
	}
}
