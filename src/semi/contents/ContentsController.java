package semi.contents;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/semi/contents.do")
public class ContentsController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cafe_num=Integer.parseInt(req.getParameter("cafe_num"));
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		ContentsDao dao=ContentsDao.getDao();
		ArrayList<Contents_ListVo>list=dao.listAll(cafe_num, startRow, endRow);
		int pageCount=(int)Math.ceil(dao.getCount(cafe_num)/10.0);
		int startPage=((pageNum-1)/5)*5+1;
		int endPage=startPage+4;
		if(endPage>pageCount){
			endPage=pageCount;
		}
		System.out.println("pageCount:"+pageCount);
		System.out.println("pageNum : "+pageNum);
		System.out.println("startPage: "+startPage);
		System.out.println("endPage: "+endPage);
		System.out.println("startRow : "+startRow);
		System.out.println("endRow : "+endRow);
		System.out.println("=======================\n");
		req.setAttribute("cp", req.getContextPath());
		req.setAttribute("cafe_num", cafe_num);
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum",pageNum);
		req.getRequestDispatcher("/contents/content.jsp").forward(req, resp);
	}
}
