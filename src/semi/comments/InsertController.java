package semi.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/comments/insert.do")
public class InsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String user_id=req.getParameter("user_id");
		String comments_content=req.getParameter("commnets_content");
		int comments_num=Integer.parseInt(req.getParameter("comments_num"));
		CommentsVo vo=new CommentsVo(comments_num,0,0,comments_content,0,0,0);
		UserVo uvo=new UserVo(0,user_id);
		CommentsDao dao=CommentsDao.getInstance();
		int n=dao.insertCom(vo);
		boolean using=false;
		if(n>0) {
			using=true;
		}
		JSONObject json=new JSONObject();
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		json.put("using", using);
		pw.print(json);
	}
}
