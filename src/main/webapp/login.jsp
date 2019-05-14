<%@ page import="Account.SocialMedia.Facebook.FBConnection" %><%--
  Created by IntelliJ IDEA.
  User: cpu11499
  Date: 14/05/2019
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    FBConnection fbConnection = new FBConnection();
%>
<html>
<head>
    <title>Login with fb</title>
</head>
<body>
<h1>login</h1>
<a href="https://www.facebook.com/dialog/oauth?client_id=2354188414857594&redirect_uri=https://localhost:8443/websocket/">Login Facebook</a>
</body>
</html>
