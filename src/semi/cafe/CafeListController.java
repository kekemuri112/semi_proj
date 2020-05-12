package semi.cafe;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cafe/cafelist.do")
public class CafeListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CafeDao dao=CafeDao.getInstance();
		ArrayList<String> cafelist=dao.listAll();
		req.setAttribute("cafelist",cafelist );
		req.getRequestDispatcher("/cafe/cafelist.jsp").forward(req, resp);
	}
}
