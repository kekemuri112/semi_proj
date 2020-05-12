package semi.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/reg/updatacontroller.do")
public class UpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String users_id=req.getParameter("users_id");
		UsersVo vo=UsersDao.getInstance().information(users_id);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
