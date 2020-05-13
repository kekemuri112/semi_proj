package semi.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/comments/delete.do")
public class DeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int comments_num=Integer.parseInt(req.getParameter("comments_num"));
		CommentsDao dao=CommentsDao.getInstance();
		int n=dao.deleteCom(comments_num);
		boolean using=false;
		if(n>0) {
			using=true;
		}
		JSONObject json=new JSONObject();
		resp.setContentType("text/plain;charset=utf-8");
		json.put("using", using);
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
