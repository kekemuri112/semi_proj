package semi.cafe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
@WebServlet("/cafe/cafestatus.do")
public class CafeStatus extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String scafe_num=req.getParameter("cafe_num");
		int cafe_num=0;
		if(scafe_num!=null) {
			cafe_num=Integer.parseInt(scafe_num);
		}
		System.out.println("cafe_num:"+cafe_num);
		CafeDao dao=CafeDao.getInstance();
		int n=dao.cafeStatus(cafe_num, false);
		System.out.println("cafeSatatus:"+n);
		boolean bl=false;
		if(n>0) {
			bl=true;
		}
		JSONObject json=new JSONObject();
		PrintWriter pw=resp.getWriter();
		json.put("result", bl);
		pw.print(json);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String scafe_num=req.getParameter("cafe_num");
		int cafe_num=0;
		if(scafe_num!=null) {
			cafe_num=Integer.parseInt(scafe_num);
		}
		CafeDao dao=CafeDao.getInstance();
		int n=dao.cafeStatus(cafe_num, true);
		boolean bl=false;
		if(n>0) {
			bl=true;
		}
		JSONObject json=new JSONObject();
		PrintWriter pw=resp.getWriter();
		json.put("result", bl);
		pw.print(json);
	}
}
