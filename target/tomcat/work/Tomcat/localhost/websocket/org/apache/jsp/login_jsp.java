/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-05-17 09:05:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Account.SocialMedia.Facebook.FBConnection;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <title>Sign in</title>\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.3.1.min.js\"></script>\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\n");
      out.write("          integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n");
      out.write("    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>\n");
      out.write("    <style>\n");
      out.write("        body{\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("            font-family: sans-serif;\n");
      out.write("            /*background-image: url(\"loginpage-background.png\");*/\n");
      out.write("            background-size: cover;\n");
      out.write("            background-repeat: no-repeat;\n");
      out.write("            opacity: 0.9;\n");
      out.write("        }\n");
      out.write("        .container {\n");
      out.write("            padding: 10px;\n");
      out.write("            display: block;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            background: #234;\n");
      out.write("            color: #fff;\n");
      out.write("            width: 25%;\n");
      out.write("            top: 50%;\n");
      out.write("            left: 50%;\n");
      out.write("            position: absolute;\n");
      out.write("            transform: translate(-50%, -50%);\n");
      out.write("        }\n");
      out.write("        h2{\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0 0 20px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("        button{\n");
      out.write("            width: 100%;\n");
      out.write("            border: 1px solid #fff;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            outline: none;\n");
      out.write("            height: 40px;\n");
      out.write("            color: #fff;\n");
      out.write("        }\n");
      out.write("        .btn-login:hover {\n");
      out.write("            opacity: 0.9;\n");
      out.write("        }\n");
      out.write("        .forgot {\n");
      out.write("            text-decoration: none;\n");
      out.write("            font-size: 12px;\n");
      out.write("            float: right;\n");
      out.write("            color: #fff;\n");
      out.write("            font-style: italic;\n");
      out.write("        }\n");
      out.write("        .forgot:hover {\n");
      out.write("            text-decoration: none;\n");
      out.write("            cursor: pointer;\n");
      out.write("            color: #38b;\n");
      out.write("        }\n");
      out.write("        .field-icon {\n");
      out.write("            float: right;\n");
      out.write("            margin-left: -25px;\n");
      out.write("            margin-top: -25px;\n");
      out.write("            position: relative;\n");
      out.write("            z-index: 2;\n");
      out.write("        }\n");
      out.write("        .social-btn .btn {\n");
      out.write("            border: none;\n");
      out.write("            margin: 5px;\n");
      out.write("            width: 35%;\n");
      out.write("            color: #fff;\n");
      out.write("            opacity: 1;\n");
      out.write("        }\n");
      out.write("        .social-btn .btn:hover {\n");
      out.write("            opacity: 0.9;\n");
      out.write("        }\n");
      out.write("        .social-btn .btn-facebook {\n");
      out.write("            background: #46a;\n");
      out.write("        }\n");
      out.write("        .social-btn .btn-github {\n");
      out.write("            background: #333;\n");
      out.write("        }\n");
      out.write("        .social-btn .btn-google {\n");
      out.write("            background: #e54;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-login {\n");
      out.write("            background: #6a5;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <h2>Sign In</h2>\n");
      out.write("    </div>\n");
      out.write("    <form name=\"login-form\" method=\"post\" action=\"login\">\n");
      out.write("        <div class=\"form-group\">\n");
      out.write("            <div class=\"input-group flex-nowrap\">\n");
      out.write("                <input type=\"text\" class=\"form-control\" placeholder=\"Username\" aria-describedby=\"addon-wrapping\"\n");
      out.write("                       id=\"username\" name=\"username\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"form-group\">\n");
      out.write("            <div class=\"input-group flex-nowrap\">\n");
      out.write("                <input type=\"password\" class=\"form-control\" placeholder=\"Password\" aria-describedby=\"addon-wrapping\"\n");
      out.write("                       id=\"password\" name=\"password\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"form-group form-check\">\n");
      out.write("            <input type=\"checkbox\" class=\"form-check-input\" id=\"remember-me\">\n");
      out.write("            <label class=\"form-check-label\" for=\"remember-me\">Remember me</label>\n");
      out.write("            <a class=\"forgot\" href=\"#\">forgot?</a>\n");
      out.write("        </div>\n");
      out.write("        <button class=\"btn-login\" type=\"submit\" id=\"btn-login\">Sign In</button>\n");
      out.write("        <p class=\"form-text text-white text-center\">\n");
      out.write("            Not yet a member? <a class=\"text-warning\" href=\"signup.html\" style=\"text-decoration: underline\">Sign up</a>\n");
      out.write("        </p>\n");
      out.write("        <div class=\"sign-in\">\n");
      out.write("            <p class=\"text-center\">Login with your social media account</p>\n");
      out.write("            <div class=\"text-center social-btn\">\n");
      out.write("                <a href=\"");
      out.print(new FBConnection().getFBAuthUrl());
      out.write("\" class=\"btn btn-facebook\"><i class=\"fab fa-facebook-f\"></i>&nbsp; Facebook</a>\n");
      out.write("                <!-- <a href=\"#\" class=\"btn btn-github\"><i class=\"fab fa-github\"></i>&nbsp; Github</a> -->\n");
      out.write("                <a href=\"");
      out.print(request.getContextPath());
      out.write("/logingg\" class=\"btn btn-google\"><i class=\"fab fa-google\"></i>&nbsp; Google</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </form>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
