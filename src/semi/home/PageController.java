package semi.home;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.cafe.CafeDao;
import semi.notice.NoticeDao;
import semi.questions.CaferegDao;
@WebServlet("/semi/pagecontroller.do")
public class PageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		int check=Integer.parseInt(req.getParameter("check"));
		req.setAttribute("cafelist", CafeDao.getInstance().listAll());
		String[] checks= {"/register/login.jsp","/register/register.jsp","/register/regsearch.jsp",
				"/home/result.jsp","/questions/answer.jsp","","/cafe/cafeapproval.jsp",
				"/questions/questions.jsp",""};
		/*
		 * 	1.로그인	2.회원가입	3.id/pwd찾기	4.로그아웃	5.카페가입
		 * 	6.카페탈퇴	7.가입한카페목록  8.카페관리  9.정보수정
		 */
		HttpSession session=req.getSession();
		switch(check) {
			//로그인
			case 1:	req.setAttribute("mfile", checks[check-1]);
				break;
			case 2:	req.setAttribute("mfile", checks[check-1]);
				break;
			case 3:	req.setAttribute("mfile", checks[check-1]);
				break;
			case 4:	req.setAttribute("mfile", checks[check-1]);
					session.removeAttribute("users_id");
					session.removeAttribute("users_num");
					req.setAttribute("msg", "로그아웃완료");
				break;
			case 5:	req.setAttribute("mfile", checks[check-1]);
				String scafe_num=req.getParameter("cafe_num");
				int cafe_num=0;
				if(scafe_num!=null) {
					cafe_num=Integer.parseInt(scafe_num);
				}
				ArrayList<String> qlist = CaferegDao.getInstance().getQuestions(cafe_num);
				req.setAttribute("qlist", qlist);
				req.setAttribute("cafe_num", cafe_num);
				break;
			case 6:	req.setAttribute("mfile", checks[check-1]);
				break;
			case 7:	req.setAttribute("mfile", checks[check-1]);
				break;
			case 8:	req.setAttribute("mfile", checks[check-1]);
				req.setAttribute("cafe_admin", req.getParameter("cafe_admin"));
				req.setAttribute("cafe_num", req.getParameter("cafe_num"));
				break;
			case 9:	req.setAttribute("mfile", checks[check-1]);
				break;
		}
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		String cafe_num=req.getParameter("cafe_num");
		if(cafe_num!=null&&!cafe_num.equals("")) {
			String snotice_num=req.getParameter("notice_num");
			int notice_num=0;
			if(snotice_num!=null) {
				notice_num=Integer.parseInt(snotice_num);
			}
			req.setAttribute("noticelist", NoticeDao.getInstance().listAll(notice_num));
			req.setAttribute("mlist", "/notice/noticelist.jsp");
		}else {
			req.setAttribute("cafelist", CafeDao.getInstance().listAll());
			req.setAttribute("mlist", "/cafe/cafelist.jsp");
		}
		
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
