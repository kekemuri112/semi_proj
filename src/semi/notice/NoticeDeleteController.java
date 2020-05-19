package semi.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
@WebServlet("/notice/delete.do")
public class NoticeDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String snotice_lev=req.getParameter("notice_lev");
		String snotice_num=req.getParameter("notice_num");
		String snotice_ref=req.getParameter("notice_ref");
		int notice_num=Integer.parseInt(snotice_num);
		int notice_lev=Integer.parseInt(snotice_lev);
		int notice_ref=Integer.parseInt(snotice_ref);
		NoticeDao dao=NoticeDao.getInstance();
		int n=dao.delNotice(notice_lev, notice_ref, notice_num);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		boolean result=false;
		if(n>0) {
			result=true;
		}
		JSONObject json=new JSONObject();
		json.put("result", result);
		pw.print(json);
	}
}
