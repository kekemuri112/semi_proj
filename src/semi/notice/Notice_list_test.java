package semi.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice/test.do")
public class Notice_list_test extends HttpServlet{
 @Override
 	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 String scafe_num=req.getParameter("cafe_num");
	 int cafe_num=Integer.parseInt(scafe_num);
	 req.getSession().setAttribute("cafe_num", cafe_num);
	 req.setAttribute("cp", req.getContextPath());	
	 req.getRequestDispatcher("/notice/noticeList.jsp").forward(req, resp);
	 	
 	}
}
