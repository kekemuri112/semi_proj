package semi.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/notice/list.do")
public class NoticeListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cafe_num=(int)req.getSession().getAttribute("cafe_num");
		resp.setContentType("text/plain;charset=utf-8");
		NoticeDao dao=NoticeDao.getInstance();
		ArrayList<NoticeVo> list=dao.listAll(cafe_num);
		JSONArray jarr=new JSONArray();
		for(NoticeVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("notice_num", vo.getNotice_num());
			json.put("cafe_num", vo.getCafe_num());
			json.put("notice_lev", vo.getNotice_lev());
			json.put("notice_ref", vo.getNotice_ref());
			json.put("notice_step", vo.getNotice_step());
			json.put("notice_name", vo.getNotice_name());
			jarr.put(json);
		}
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
}
