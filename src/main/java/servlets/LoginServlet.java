package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (userService.validateUser(login, password)) {
            HttpSession session = req.getSession();
            User user = userService.getUserByLogin(login);
            session.setAttribute("role",user.getRole());
            if(session.getAttribute("role").equals("admin")){
                resp.sendRedirect("/admin");
            }else {
                resp.sendRedirect("user.jsp");
            }

        } else {
            resp.sendRedirect("/login");
        }
    }
}