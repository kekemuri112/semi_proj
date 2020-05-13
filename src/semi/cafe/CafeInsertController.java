package semi.cafe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.omg.CORBA.portable.OutputStream;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
@WebServlet("/semi/cafeinsert.do")
public class CafeInsertController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String upload=req.getServletContext().getRealPath("/upload");
		System.out.println("path:" +upload);
		MultipartRequest mr=new MultipartRequest(
					req, 
					upload, 
					1024*1024*10, 
					"utf-8",
					new DefaultFileRenamePolicy()
				);
		// imagepath 경로
		String imagepath=upload+"/"+mr.getFilesystemName("image");
		/*
		 * 시간남을때 할 파일저장(컬럼:blob) 처리방법
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		FileInputStream fis=new FileInputStream(imagepath);
		
		byte[] buf=new byte[1024];
		int read=0;
		
		while((read=fis.read(buf, 0, buf.length)) !=-1) {
			baos.write(buf, 0, read);
		}
		*/
		String cafe_name=mr.getParameter("cafe_name");
		String cafe_desc=mr.getParameter("cafe_desc");
		String cafe_intent=mr.getParameter("cafe_intent");
		//String cafe_image=req.getParameter("cafe_image");
		CafeVo vo=new CafeVo(0,cafe_name,cafe_desc,cafe_intent,null,null,imagepath);
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
