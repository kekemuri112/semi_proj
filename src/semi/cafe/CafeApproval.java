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
@WebServlet("/cafe/cafeapproval.do")
public class CafeApproval extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		String spageNum = req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		CafeDao dao=CafeDao.getInstance();
		ArrayList<CafeVo> appList=dao.approvalList(startRow, endRow);
		int pageCount= (int)Math.ceil(dao.getCount()/10.0);
		int startPage=((pageNum-1)/10)*10+1;
		int endPage=startPage+9;
		if(endPage>pageCount){
			endPage=pageCount;
		}
		JSONArray jarr=new JSONArray();
		for(CafeVo vo:appList) {
			JSONObject json=new JSONObject();
			json.put("cafe_num",vo.getCafe_num());
			json.put("cafe_name",vo.getCafe_name());
			json.put("cafe_desc",vo.getCafe_desc());
			json.put("cafe_intent",vo.getCafe_intent());
			json.put("cafe_admin",vo.getCafe_admin());
			jarr.put(json);
		}
		JSONObject json=new JSONObject();
		json.put("pageCount", pageCount);
		json.put("startPage", startPage);
		json.put("endPage", endPage);
		json.put("pageNum",pageNum);
		jarr.put(json);
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
	
	

}
