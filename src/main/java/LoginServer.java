import Entity.User;
import EntityDAO.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean isCorrect = false;

        while (!isCorrect){
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.println("username: " + username);
            System.out.println("password: " + password);

            UserDAO dao = new UserDAO();

            User user = dao.getUser(1);

            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                response.sendRedirect("?username=" + username);
                isCorrect = true;
            } else {
                JOptionPane.showMessageDialog(null, "Username or password is incorrect!");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }
}
