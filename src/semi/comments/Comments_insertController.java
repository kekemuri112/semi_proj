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
		resp.setContentType("text/plain;charset=utf-8");
		String scontents_num=req.getParameter("contents_num");
		int contents_num=0;
		if(scontents_num!=null) {
			contents_num=Integer.parseInt(scontents_num);
		}
		String comments_content=req.getParameter("comments_content");
		int users_num=(int)req.getSession().getAttribute("users_num");
		CommentsDao dao=CommentsDao.getInstance();
		int comments_num=0;
		int comments_ref=0;
		int comments_lev=0;
		int comments_step=0;
		String scomments_num=req.getParameter("comments_num");
		if(scomments_num!=null) {
			comments_num=Integer.parseInt(scomments_num);
			Comments_infoVo vo=dao.getInfo(comments_num);
			contents_num=vo.getContents_num();
			comments_ref=vo.getComments_ref();
			comments_lev=vo.getComments_lev();
			comments_step=vo.getComments_step();
		}
		CommentsVo vo=new CommentsVo(comments_num, contents_num, users_num, comments_content, comments_ref, comments_lev, comments_step);
		int n=dao.insert(vo);
		boolean using=false;
		if(n>0) {
			dao.update_point(users_num);
			using=true;
		}
		JSONObject json=new JSONObject();
		PrintWriter pw=resp.getWriter();
		json.put("using", using);
		pw.print(json);
	}
}
