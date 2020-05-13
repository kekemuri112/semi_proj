package semi.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class NoticeDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int notice_num=Integer.parseInt(req.getParameter("notice_num"));
		NoticeDao dao=NoticeDao.getInstance();
		int n=dao.delete(notice_num);
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
