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
public class Comments_deleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String scomments_num=req.getParameter("comments_num");
		int comments_num=Integer.parseInt(scomments_num);
		CommentsDao dao=CommentsDao.getInstance();
		String susers_id=req.getParameter("users_id");
		String my_id=(String)req.getSession().getAttribute("users_id");
		boolean result=false;
		if(susers_id.equals(my_id)) {
			int n=dao.delete_comments(comments_num);
			if(n>0) {
				result=true;
			}
		}
		JSONObject json=new JSONObject();
		json.put("result", result);
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}