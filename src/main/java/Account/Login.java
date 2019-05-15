package Account;

import DAO.UserDAO;
import Entity.User;
import Utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.html");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = UserDAO.getUser(username);

        //invalid account
        if (user == null){
            PrintWriter writer = response.getWriter();

            writer.println("<script>alert('Invalid username or password')</script>");

            RequestDispatcher dispatcher= this.getServletContext().getRequestDispatcher("/login.html");

            dispatcher.forward(request, response);
        } else if (user.getPassword().equals(password)){
            AppUtils.storeLoginedUser(request.getSession(), user);
            response.sendRedirect(request.getContextPath() + "/?username=" +user.getUsername());
        }
    }
}