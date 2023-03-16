package myServlet;

import Syeta.ExplorerService;
import Syeta.People;
import Syeta.UserService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = {"/"})
public class FileServlet extends HttpServlet {
    private final ExplorerService explorerService = new ExplorerService();
    private final UserService userService = UserService.getService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = null;
        People user = userService.getUserFromCookie(req.getSession().getId());

        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }
        String path = req.getParameter("path");
        file = explorerService.getUserFiles(user.getLogin(), path);

        req.setAttribute("name", file.getPath().substring(12));
        req.setAttribute("path", file.toPath());

        resp.setContentType("text/html");
        req.setAttribute("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")));
        if (file.isDirectory()) {
            req.setAttribute("files", file.listFiles());
        } else {
            download(resp, file);
        }
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    private void download( HttpServletResponse resp, File file) {

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition","attachment;filename=" + file.getName());
        try
        {
            FileInputStream fileIn = new FileInputStream(file);
            ServletOutputStream out = resp.getOutputStream();
            byte[] outputByte = new byte[4096];
            while(fileIn.read(outputByte, 0, 4096) != -1)
            {
                out.write(outputByte, 0, 4096);
            }
            fileIn.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        userService.removeSession(req.getSession().getId());
        for (Cookie cookie : cookies) {
            cookie.setValue("");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        resp.sendRedirect("/login");
    }
}