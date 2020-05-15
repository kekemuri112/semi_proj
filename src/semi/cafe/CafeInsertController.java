package semi.cafe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

//json
@WebServlet("/cafe/cafeinsert.do")
public class CafeInsertController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		req.getSession().setAttribute("mfile", "/cafe/cafeInsert.jsp");
		req.getRequestDispatcher("home/main.do").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String cafe_name=req.getParameter("cafe_name");
		String cafe_desc=req.getParameter("cafe_desc");
		String cafe_intent=req.getParameter("cafe_intent");
		String cafe_image=req.getParameter("cafe_image");
		CafeVo vo=new CafeVo(0,cafe_name,cafe_desc,cafe_intent,null,"승인대기",cafe_image);
		CafeDao dao=CafeDao.getInstance();
		int n=dao.insert(vo);
		if(n>0) {
			req.setAttribute("msg", "개설요청완료");
		}else {
			req.setAttribute("msg", "요청실패");
		}
		req.getSession().setAttribute("mfile", "/home/main.jsp");
		req.getRequestDispatcher("/home/main.do").forward(req, resp);
	}
	
}
