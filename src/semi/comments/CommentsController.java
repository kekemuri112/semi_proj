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

import semi.contents.Contents_detailVo;
@WebServlet("/comments/comments.do")
public class CommentsController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int contents_num=Integer.parseInt(req.getParameter("contents_num"));
		int users_num=Integer.parseInt(req.getParameter("users_num"));
		/*
		req.setAttribute("vo", vo);
		req.setAttribute("list", comList);
		req.getRequestDispatcher("/comments/comments.jsp").forward(req, resp);*/
		int pageNum=1;
	
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		String spageNum=req.getParameter("pageNum");
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		System.out.println("comments pageNum : "+pageNum);
		CommentsDao dao=CommentsDao.getInstance();
		UserVo vos=dao.getUserId(users_num);
		System.out.println("comments users_num : "+users_num);
		ArrayList<CommentsVo> comList=dao.comList(contents_num,startRow,endRow);
		JSONArray jarr=new JSONArray();
		for(CommentsVo vo1:comList) {
			JSONObject json=new JSONObject();
			System.out.println("comments_num : "+vo1.getComments_num());
			json.put("users_id", vos.getUsers_id());
			json.put("comments_num", vo1.getComments_num());
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
