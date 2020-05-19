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
@WebServlet("/notice/insert.do")
public class NoticeInsertController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		int cafe_num=(int)req.getSession().getAttribute("cafe_num");
		String notice_name=req.getParameter("notice_name");
		int notice_ref=0;
		String snotice_ref=req.getParameter("notice_ref");
		if(snotice_ref!=null) {
			notice_ref=Integer.parseInt(snotice_ref);
		}
		NoticeDao dao=NoticeDao.getInstance();
		int n=dao.insert(cafe_num,notice_ref,notice_name);
		JSONObject json=new JSONObject();
		boolean result=false;
		if(n>0) {
			result=true;
		}
		json.put("result", result);
		PrintWriter pw=resp.getWriter();
		pw.print(json);	
	}
}
