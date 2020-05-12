package semi.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/notice/notice.do")
public class NoticeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath=req.getContextPath();
		req.getServletContext().setAttribute("cp", contextPath);
		resp.sendRedirect(req.getContextPath()+"#");
	}
}
