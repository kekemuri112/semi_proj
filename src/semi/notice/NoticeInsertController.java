package semi.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
//
@WebServlet("/notice/noticeinsert.do")
public class NoticeInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		int cafe_num=Integer.parseInt(req.getParameter("cafe_num"));
		String notice_name=req.getParameter("notice_name");
		int notice_lev=Integer.parseInt(req.getParameter("notice_lev"));
		int notice_ref=Integer.parseInt(req.getParameter("notice_ref"));
		int notice_step=Integer.parseInt(req.getParameter("notice_step"));
		int notice_grade=Integer.parseInt(req.getParameter("notice_grade"));
		NoticeVo vo=new NoticeVo(0,cafe_num,notice_name,notice_lev,notice_ref,notice_step,notice_grade);
		NoticeDao dao=NoticeDao.getInstance();
		int n=dao.insert(vo);
		JSONObject json=new JSONObject();
		boolean bl=false;
		if(n>0) {
			bl=true;
		}
		json.put("bl", bl);
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		
	}
}
