package semi.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.cafe.CafeDao;
import semi.notice.NoticeDao;

@WebServlet("/reg/registercontroller.do")
public class RegisterController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String users_id = req.getParameter("users_id");				
		String users_pwd = req.getParameter("users_pwd");				
		String users_name = req.getParameter("users_name");				
		String users_email = req.getParameter("users_email");				
		String users_birth = req.getParameter("users_birth");
		String users_phone = req.getParameter("users_phone");
		UsersVo vo=new UsersVo(0, users_id, users_pwd, users_name, users_email, users_birth, users_phone);
		UsersDao dao=UsersDao.getInstance();
		int n=dao.insert(vo);
		if(n>0) {
			req.setAttribute("msg", "회원 가입이 완료되었습니다.");
		}else {
			req.setAttribute("msg", "회원 가입이 실패하였습니다.");
		}
		
		req.setAttribute("header1", "/home/wraphome.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("mlist", "/cafe/cafelist.jsp");
		int cafe_num= (Integer)req.getSession().getAttribute("cafe_num");
		if(cafe_num>0) {
			//맨날지워짐-개색기
			req.setAttribute("mlist", "/notice/noticelist.jsp");
		}else {
			req.setAttribute("cafelist", CafeDao.getInstance().listAll());
			req.setAttribute("mlist", "/cafe/cafelist.jsp");
		}
		req.setAttribute("mfile", "/home/result.jsp");
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
	}
}
