package semi.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/comments/update.do")
public class Comments_updateController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String comments_content=req.getParameter("comments_content");
		int comments_num=Integer.parseInt(req.getParameter("comments_num"));
		CommentsDao dao=CommentsDao.getInstance();
		int n=dao.update(comments_content, comments_num);
		boolean result=false;
		if(n>0) {
			result=true;
		}
		JSONObject json=new JSONObject();
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		json.put("result", result);
		pw.print(json);
		
	}
}
