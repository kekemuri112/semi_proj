package semi.comments;

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
@WebServlet("/semi/comments.do")
public class CommentsController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int contents_num=Integer.parseInt(req.getParameter("contents_num"));
		CommentsDao dao=CommentsDao.getInstance();
		ArrayList<CommentsVo> comList=dao.comList(contents_num);
		JSONArray jarr=new JSONArray();
		for(CommentsVo vo:comList) {
			JSONObject json=new JSONObject();
			json.put("contents_num", vo.getContents_num());
			json.put("users_num", vo.getUsers_num());
			json.put("comments_content", vo.getComments_content());
			json.put("comments_ref", vo.getComments_ref());
			json.put("comments_lev", vo.getComments_lev());
			json.put("comments_step", vo.getComments_step());
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
}
