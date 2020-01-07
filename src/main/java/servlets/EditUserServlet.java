package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edituser")
public class EditUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        User user = userService.getUserById(id);
        req.setAttribute("id", user.getId());
        req.setAttribute("login", user.getLogin());
        req.setAttribute("password", user.getPassword());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("role", user.getRole());
        req.getRequestDispatcher("edituser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");

        userService.editUser(new User(id, login, password, email, role));
        resp.setStatus(200);
        resp.sendRedirect("/admin");


    }
}
