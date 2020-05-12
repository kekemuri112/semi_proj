package semi.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
@WebServlet("/notice/noticeinsert.do")
public class NoticeInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String notice_name=req.getParameter("notice_name");
		String notice_lev=req.getParameter("notice_lev");
		String notice_ref=req.getParameter("notice_ref");
		String notice_step=req.getParameter("notice_step");
		String notice_grade=req.getParameter("notice_grade");
		NoticeVo vo=new NoticeVo(0,0,notice_name,notice_lev,notice_ref,notice_step,notice_grade);
		NoticeDao dao=NoticeDao.getInstance();
		int n=dao.insert(vo);
		JSONObject json=new JSONObject();
		boolean bl=false;
		if(n>0) {
			bl=true;
		}
		json.put("bl", bl);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		
	}
}
