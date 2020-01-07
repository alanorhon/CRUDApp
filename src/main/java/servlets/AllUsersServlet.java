package servlets;

import model.User;
import service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AllUsersServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = userService.getAllUsers();
        req.setAttribute("list", allUsers);
        req.getRequestDispatcher("allusers.jsp").forward(req, resp);
    }
}