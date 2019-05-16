package Account.SocialMedia.Facebook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/loginfb")
public class LoginWithFB extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code ="";

        code = request.getParameter("code");

        if (code == null || code.equals("")){
            throw new RuntimeException("ERROR: Didn't get code parameter in call back");
        }

        FBConnection fbcnn = new FBConnection();
        String accessToken = fbcnn.getAccessToken(code);

        FBGraph fbGraph = new FBGraph(accessToken);
        String graph = fbGraph.getFBGraph();
        Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
        ServletOutputStream out = response.getOutputStream();
        out.println("<h1>Facebook Login using Java</h1>");
        out.println("<h2>Application Main Menu</h2>");
        out.println("<div>Welcome "+fbProfileData.get("first_name"));
        out.println("<div>Your Email: "+fbProfileData.get("email"));
        out.println("<div>You are "+fbProfileData.get("gender"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.equals("")){
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            FBConnection fbCnn = new FBConnection();
            String accessToken = fbCnn.getAccessToken(code);
            FBGraph fbGraph = new FBGraph(accessToken);
            String graph = fbGraph.getFBGraph();
            Map fbProfileData = fbGraph.getGraphData(graph);
            ServletOutputStream out = response.getOutputStream();

            out.println("<h1>Facebook Login using Java</h1>");
            out.println("<h2>Application Main Menu</h2>");
            out.println("<div>Welcome "+fbProfileData.get("first_name"));
            out.println("<div>Your Email: "+fbProfileData.get("email"));
            out.println("<div>You are "+fbProfileData.get("gender"));

            RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
            dis.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        doPost(request, response);
    }
}
