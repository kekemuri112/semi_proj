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
import semi.questions.CaferegVo;
import semi.reg.UsersDao;
import semi.reg.UsersVo;
@WebServlet("/semi/pagecontroller.do")
public class PageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		int check=Integer.parseInt(req.getParameter("check"));
		req.setAttribute("cafelist", CafeDao.getInstance().listAll());
		String[] checks= {"/register/login.jsp","/register/register.jsp","/register/regsearch.jsp",
				"/home/result.jsp","/questions/answer.jsp","/cafe/userscafedel.jsp","/cafe/cafeapproval.jsp",
				"/questions/cafeset.jsp","/register/regupdate.jsp"};
		
		/*
		 * 	1.로그인	2.회원가입	3.id/pwd찾기	4.로그아웃	5.카페가입
		 * 	6.카페탈퇴	7.가입한카페목록  8.카페관리  9.정보수정
		 */
		HttpSession session=req.getSession();
		int cafe_num=(int)req.getSession().getAttribute("cafe_num");
		
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
				ArrayList<CaferegVo> qlist = CaferegDao.getInstance().getQuestions(cafe_num);
				req.setAttribute("qlist", qlist);
				break;
			case 6:	req.setAttribute("mfile", checks[check-1]);
				String cafe_name=CafeDao.getInstance().getVo(cafe_num).getCafe_name();
				req.setAttribute("cafe_name", cafe_name);
				break;
			case 7:	req.setAttribute("mfile", checks[check-1]);
				break;
			case 8:	req.setAttribute("mfile", checks[check-1]);
				req.setAttribute("cafe_admin", req.getParameter("cafe_admin"));
				break;
			case 9:	req.setAttribute("mfile", checks[check-1]);
				String users_id=(String)session.getAttribute("users_id");
				UsersVo vo= UsersDao.getInstance().information(users_id);
				req.setAttribute("vo", vo);
				break;
		}
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		if(cafe_num!=0 ) {
			req.setAttribute("mlist", "/notice/noticelist.jsp");
		}else {
			req.setAttribute("cafelist", CafeDao.getInstance().listAll());
			req.setAttribute("mlist", "/cafe/cafelist.jsp");
		}
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
