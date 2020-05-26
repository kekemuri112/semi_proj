package semi.cafe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
@WebServlet("/cafe/accept.do")
public class UsersAccepterController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String susers_cafe_num=req.getParameter("users_cafe_num");
		int users_cafe_num=Integer.parseInt(susers_cafe_num);
		UsersApprovalDao dao=UsersApprovalDao.getInstance();
		int n=dao.accept(users_cafe_num);
		boolean result=false;
		if(n>0) {
			result=true;
		}
		JSONObject json=new JSONObject();
		json.put("result", result);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
