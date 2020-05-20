package semi.chat;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/chat/shoutServlet.do"}, asyncSupported=true)
public class WebChat extends HttpServlet {
    private List<AsyncContext> contexts = new LinkedList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final AsyncContext asyncContext = req.startAsync(req, resp);
        asyncContext.setTimeout(10 * 60 * 1000);
        contexts.add(asyncContext);
        System.out.println("Here is Get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<AsyncContext> asyncContexts = new ArrayList<>(this.contexts);
        this.contexts.clear();
        String name = req.getParameter("name");
        String message = req.getParameter("message");
        String htmlMessage = name + " : "+ message;
        ServletContext sc = req.getServletContext();
        if (sc.getAttribute("messages") == null) {
            sc.setAttribute("messages", htmlMessage);
        } else {
            String currentMessages = (String) sc.getAttribute("messages");
            sc.setAttribute("messages", htmlMessage + currentMessages);
        }
        for (AsyncContext asyncContext : asyncContexts) {
            try (PrintWriter writer = asyncContext.getResponse().getWriter()) {
                writer.println(htmlMessage);
                writer.println((int)req.getSession().getAttribute("cafe_num"));
                writer.flush();
                asyncContext.complete();
            } catch (Exception ex) {
            	ex.printStackTrace();
            }
        }

    }
}