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
		int users_num=Integer.parseInt(req.getParameter("users_num"));
		CommentsDao dao=CommentsDao.getInstance();
		ArrayList<CommentsVo> comList=dao.comList(contents_num);
		UserVo vo=dao.getUserId(users_num);
		JSONArray jarr=new JSONArray();
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/comments/comments.jsp").forward(req, resp);
		for(CommentsVo vo1:comList) {
			JSONObject json=new JSONObject();
			json.put("contents_num", vo1.getContents_num());
			json.put("users_num", vo1.getUsers_num());
			json.put("comments_content", vo1.getComments_content());
			json.put("comments_ref", vo1.getComments_ref());
			json.put("comments_lev", vo1.getComments_lev());
			json.put("comments_step", vo1.getComments_step());
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
}
