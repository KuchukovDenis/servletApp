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
    File file;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        file = File.listRoots()[0];
        req.setAttribute("name", file.getPath());
        req.setAttribute("files", file.listFiles());
        req.getRequestDispatcher("main.jsp").forward(req, resp);
        resp.setContentType("text/html");
        req.setAttribute("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterValues("btnBack") != null){
            file = file.getParentFile();
        }
        else {
            file = new File(req.getParameterValues("btn")[0]);
        }
        if (file == null){
            file = File.listRoots()[0];
        }
        if(!file.isDirectory()){
            file = file.getParentFile();
        }
        req.setAttribute("name", file.getPath());
        req.setAttribute("files", file.listFiles());
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}