package semi.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/notice/notice.do")
public class Notice extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String snotice_num=req.getParameter("notice_num");
		int notice_num=Integer.parseInt(snotice_num);
		System.out.println("notice_num : "+notice_num);
		NoticeDeleteDao dao=NoticeDeleteDao.getInstance();
		dao.delete_notice(notice_num);
		
	}
}
