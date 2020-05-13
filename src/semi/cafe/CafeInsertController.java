package semi.cafe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
@WebServlet("/semi/cafeinsert.do")
public class CafeInsertController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cafe_name=req.getParameter("cafe_name");
		String cafe_desc=req.getParameter("cafe_desc");
		String cafe_intent=req.getParameter("cafe_intent");
		String cafe_image=req.getParameter("cafe_image");
		CafeVo vo=new CafeVo(0,cafe_name,cafe_desc,cafe_intent,"¾îµå¹Î","½ÂÀÎ´ë±â",cafe_image);
		CafeDao dao=CafeDao.getInstance();
		int n=dao.insert(vo);
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
