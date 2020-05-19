package semi.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reg/updatecontroller.do")
public class UpdateController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		UsersVo vo=new UsersVo();
		vo.setUsers_num(Integer.parseInt(req.getParameter("users_num")));
		vo.setUsers_id(req.getParameter("users_id"));
		vo.setUsers_name(req.getParameter("users_name"));
		vo.setUsers_pwd(req.getParameter("users_pwd"));
		vo.setUsers_email(req.getParameter("users_email"));
		vo.setUsers_birth(req.getParameter("users_birth"));
		vo.setUsers_phone(req.getParameter("users_phone"));
		
		int n=UsersDao.getInstance().update(vo);
		if(n>0) {
			req.setAttribute("msg", "수정완료");
		}else {
			req.setAttribute("msg", "수정실패..");
		}
		int cafe_num=(int)req.getSession().getAttribute("cafe_num");
		if(cafe_num!=0) {
			req.setAttribute("mfile", "/notice/notice.jsp");
		}else {
			req.setAttribute("mlist", "/cafe/cafelist.jsp");
		}
		req.setAttribute("headerLog", "/register/rmain.jsp");
		req.setAttribute("header2", "/home/wrapmain.jsp");
		req.setAttribute("mfile", "/home/result.jsp");		
		req.getRequestDispatcher("/home/main.jsp").forward(req, resp);
		
	}
	
}
