package myServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = {"/"})
public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = null;
        String path = req.getParameter("path");
        if (path != null && path.equals("/")) {
            file = File.listRoots()[0];
        } else if (path == null) {
            resp.sendRedirect("?path=/");
            return;
        } else {
            file = new File(path);
        }


        req.setAttribute("name", file.getPath());
        req.setAttribute("files", file.listFiles());
        req.setAttribute("path", file.toPath());

        resp.setContentType("text/html");
        req.setAttribute("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")));
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}