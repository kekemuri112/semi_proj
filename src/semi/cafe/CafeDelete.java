package semi.cafe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class CafeDelete extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int cafe_num=Integer.parseInt(req.getParameter("cafe_num"));
		CafeDao dao=CafeDao.getInstance();
		int n=dao.delete(cafe_num);
		JSONObject json=new JSONObject();
		boolean bl=false;
		if(n>0) {
			bl=true;
		}
		json.put("bl", bl);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
