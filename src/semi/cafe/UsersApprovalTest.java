package semi.cafe;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cafe/test.do")
public class UsersApprovalTest extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("cp", req.getContextPath());
		req.getSession().setAttribute("cafe_num", 2882);
		req.getRequestDispatcher("/cafe/usersapproval.jsp").forward(req, resp);
	}
}
