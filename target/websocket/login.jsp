<%--
  Created by IntelliJ IDEA.
  User: cpu11499
  Date: 14/05/2019
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login with fb</title>
</head>
<body>
<h1>login</h1>
<%=request.getContextPath()%>
<a href="https://www.facebook.com/dialog/oauth?client_id=2354188414857594&redirect_uri=http://localhost:8080/websocket/">Login Facebook</a>
</body>
</html>
