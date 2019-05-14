package Security;

import Entity.User;
import Utils.AppUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getServletPath();

        //user information stored in the session
        User loginedUser = AppUtils.getLoginedUser(request.getSession());

        if (path.equals("/login")){
            filterChain.doFilter(request, response);
            return;
        }

        if (loginedUser != null) {
            filterChain.doFilter(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login");

            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
