package semi.contents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contents/delete.do")
public class Contents_DeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String scontents_num=req.getParameter("contents_num");
		int contents_num=Integer.parseInt(scontents_num);
		System.out.println("삭제컨트롤러 contents_num : "+contents_num);
		ContentsDao dao=ContentsDao.getDao();
		int n=dao.delete(contents_num);
		if(n>0) {
			req.setAttribute("msg", "삭제완료");
		}else {
			req.setAttribute("msg", "삭제실패");
		}
		
		
		
		
	}
}
