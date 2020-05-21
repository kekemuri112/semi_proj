package semi.cafe;

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
@WebServlet("/cafe/usersapproval.do")
public class UsersApprovalController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersApprovalDao dao=UsersApprovalDao.getInstance();
		int cafe_num=(int)req.getSession().getAttribute("cafe_num");
		int pageNum=1;
		String spageNum=req.getParameter("pageNum");
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10;
		int endRow=startRow+9;
		
		int pageCount=(int)Math.ceil(dao.getCount(cafe_num)/10.0); // 총페이지갯수
		int startPage=((pageNum-1)/5)*5+1;
		int endPage=startPage+4;
		
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		ArrayList<UsersApprovalVo>list=dao.getInfo(cafe_num,startRow,endRow);
		JSONArray jarr=new JSONArray();
		for(UsersApprovalVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("users_num", vo.getUsers_num());
			json.put("users_id", vo.getUsers_id());
			json.put("users_pwd", vo.getUsers_pwd());
			json.put("users_name", vo.getUsers_name());
			json.put("users_email", vo.getUsers_email());
			json.put("users_birth", vo.getUsers_birth());
			json.put("users_phone", vo.getUsers_phone());
			json.put("users_cafe_num", vo.getUsers_cafe_num());
			json.put("cafe_num", vo.getCafe_num());
			json.put("users_cafe_point", vo.getUsers_cafe_point());
			json.put("users_cafe_approved", vo.getUsers_cafe_approved());
			jarr.put(json);
		}
		JSONObject json=new JSONObject();
		json.put("pageNum", pageNum);
		json.put("startPage", startPage);
		json.put("endPage", endPage);
		json.put("pageCount", pageCount);
		jarr.put(json);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
}
