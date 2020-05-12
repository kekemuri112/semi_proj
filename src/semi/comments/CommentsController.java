package semi.comments;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/semi/comments.do")
public class CommentsController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int contents_num=Integer.parseInt(req.getParameter("contents_num"));
		CommentsDao dao=CommentsDao.getInstance();
		ArrayList<CommentsVo> comList=dao.comList(startRow, endRow)("contents_num");
	}
}
