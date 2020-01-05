package servlets;

import model.User;
import service.UserService;
import utils.FormGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/allusers")
public class AllUsersServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> result = userService.getAllUsers();
        StringBuilder sb = new StringBuilder();
        result.forEach(x -> sb
                .append(x.toString())
                .append("<br/>")
                .append(FormGenerator.getDeleteForm(x))
                .append(FormGenerator.getUpdateForm(x))
                .append("<br/>"));
        String answer = sb.toString();
        req.setAttribute("message", answer.isEmpty() ? "Users DB is empty" : answer);
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }

}