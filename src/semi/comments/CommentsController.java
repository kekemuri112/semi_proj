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
		int pageNum=1;
		String spageNum=req.getParameter("pageNum");
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		CommentsDao dao=CommentsDao.getInstance();
		int pageCount=(int)Math.ceil((dao.getCount(contents_num)/5.0));
		int startPage=((pageNum-1)/5)*5+1;
		int endPage=startPage+4;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		System.out.println("comments pageNum : "+pageNum);
		System.out.println("comments startPage:"+startPage);
		System.out.println("comments pageCount: "+pageCount);
		ArrayList<CommentsVo> comList=dao.comList(contents_num,startRow,endRow);
		JSONArray jarr=new JSONArray();
		for(CommentsVo vo1:comList) {
			JSONObject json=new JSONObject();
			System.out.println("comments_num : "+vo1.getComments_num());
			int users_num1=vo1.getUsers_num();
			UserVo vo2=dao.getUserId(users_num1);
			json.put("users_id", vo2.getUsers_id());
			json.put("comments_num", vo1.getComments_num());
			json.put("contents_num", vo1.getContents_num());
			json.put("users_num", vo1.getUsers_num());
			json.put("comments_content", vo1.getComments_content());
			json.put("comments_ref", vo1.getComments_ref());
			json.put("comments_lev", vo1.getComments_lev());
			json.put("comments_step", vo1.getComments_step());
			jarr.put(json);
		}
		JSONObject json=new JSONObject();
		json.put("startPage", startPage);
		json.put("endPage", endPage);
		json.put("pageNum",pageNum );
		json.put("pageCount", pageCount);
		jarr.put(json);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
}
