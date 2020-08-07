package semi.cafe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/cafe/getAnswer11.do")
public class UsersPopupTestController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String susers_cafe_num=req.getParameter("users_cafe_num");
		int users_cafe_num=0;
		if(susers_cafe_num!=null) {
			users_cafe_num=Integer.parseInt(susers_cafe_num);
		}
		UsersApprovalDao dao=UsersApprovalDao.getInstance();
		dao.getAnswer(users_cafe_num);
		resp.setContentType("utf-8/plain;charset=utf-8");
		JSONObject json =new JSONObject();
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
