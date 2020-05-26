package semi.reg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/reg/emailcheck.do")
public class EmailcheckController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("test/plain;charset=utf-8");
		String users_email=req.getParameter("users_email");
		int n=UsersDao.getInstance().emailCheck(users_email);
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		if(n>0) {
			pw.print("<emailcheck>false</emailcheck>");
		}else {
			pw.print("<emailcheck>true</emailcheck>");
		}
		pw.print("</result>");
	}
}
