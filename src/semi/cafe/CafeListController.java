package semi.cafe;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CafeListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cafe_num1=req.getParameter("cafe_num");
		try {
			int cafe_num2=Integer.parseInt(cafe_num1);
			CafeDao dao=CafeDao.getInstance();
			ArrayList<String>cafelist=dao.listall();
			req.setAttribute("cafelist",cafelist );
			req.getRequestDispatcher("#").forward(req, resp);
		}catch (NumberFormatException ne) {
			ne.printStackTrace();
			resp.sendRedirect("#");
		}
	}
}
