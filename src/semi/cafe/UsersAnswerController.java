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

@WebServlet("/cafe/getAnswer.do")
public class UsersAnswerController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String susers_cafe_num=req.getParameter("users_cafe_num");
		int users_cafe_num=Integer.parseInt(susers_cafe_num);
		UsersApprovalDao dao=UsersApprovalDao.getInstance();
		ArrayList<UsersAnswerVo>list=dao.getAnswer(users_cafe_num);
		JSONArray jarr=new JSONArray();
		for(UsersAnswerVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("users_cafe_num",vo.getUsers_cafe_num());
			json.put("cafereg_num", vo.getCafereg_num());
			json.put("cafereg_question", vo.getCafereg_question());
			json.put("answer_contents", vo.getAnswer_contents());
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
}
