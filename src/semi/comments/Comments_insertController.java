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
public class Comments_insertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String scontents_num=req.getParameter("contents_num");
		System.out.println("scontents_num : "+scontents_num);
		int contents_num=Integer.parseInt(scontents_num);
		String comments_content=req.getParameter("comments_content");
		int users_num=(int)req.getSession().getAttribute("users_num");
		CommentsDao dao=CommentsDao.getInstance();
		int n=dao.insert(contents_num,comments_content,users_num);
		boolean using=false;
		if(n>0) {
			dao.update_point(users_num);
			using=true;
		}
		JSONObject json=new JSONObject();
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		json.put("using", using);
		pw.print(json);
	}
}
