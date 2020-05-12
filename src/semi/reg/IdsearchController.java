package semi.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/idsearchController.do")
public class IdsearchController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String users_name=req.getParameter("users_name");
		String users_email=req.getParameter("users_email");
		String id=UsersDao.getInstance().search(null, users_name, users_email);
		String[] ids=id.split("");
		String users_id="";
		for(int i=0;i<ids.length;i++) {
			if(i>3 && i<6) {
				users_id+="*";
			}else {
				users_id+=ids[i];
			}
		}
		req.setAttribute("users_id", users_id);
		req.getRequestDispatcher("").forward(req, resp);
	}
}
