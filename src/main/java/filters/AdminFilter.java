package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/deleteuser", "/edituser", "/adduser", "/admin", "/user"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String userRole = (String) request.getSession(false).getAttribute("role");
        if (userRole != null && userRole.equals("admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (userRole == null) {
            request.setAttribute("message", "Please login");
            request.getRequestDispatcher("/login").forward(request, response);
        } else {
            String name = (String) request.getSession(false).getAttribute("login");
            request.setAttribute("message", "Welcome " + name);
            request.getRequestDispatcher("/user").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}