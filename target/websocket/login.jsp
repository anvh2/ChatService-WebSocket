<%@ page import="Account.SocialMedia.Facebook.FBConnection" %><%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: cpu11499--%>
<%--  Date: 14/05/2019--%>
<%--  Time: 16:47--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Login with fb</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>login</h1>--%>
<%--<a href="<%=request.getContextPath()%>/logout">Login</a>--%>
<%--<a href="https://www.facebook.com/dialog/oauth?client_id=2354188414857594&redirect_uri=http://localhost:8080/websocket/">Login Facebook</a>--%>
<%--</body>--%>
<%--</html>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
    <style>
        body{
            margin: 0;
            padding: 0;
            font-family: sans-serif;
            /*background-image: url("loginpage-background.png");*/
            background-size: cover;
            background-repeat: no-repeat;
            opacity: 0.9;
        }
        .container {
            padding: 10px;
            display: block;
            border-radius: 10px;
            background: #234;
            color: #fff;
            width: 25%;
            top: 50%;
            left: 50%;
            position: absolute;
            transform: translate(-50%, -50%);
        }
        h2{
            margin: 0;
            padding: 0 0 20px;
            text-align: center;
        }
        button{
            width: 100%;
            border: 1px solid #fff;
            border-radius: 5px;
            outline: none;
            height: 40px;
            color: #fff;
        }
        .btn-login:hover {
            opacity: 0.9;
        }
        .forgot {
            text-decoration: none;
            font-size: 12px;
            float: right;
            color: #fff;
            font-style: italic;
        }
        .forgot:hover {
            text-decoration: none;
            cursor: pointer;
            color: #38b;
        }
        .field-icon {
            float: right;
            margin-left: -25px;
            margin-top: -25px;
            position: relative;
            z-index: 2;
        }
        .social-btn .btn {
            border: none;
            margin: 5px;
            width: 35%;
            color: #fff;
            opacity: 1;
        }
        .social-btn .btn:hover {
            opacity: 0.9;
        }
        .social-btn .btn-facebook {
            background: #46a;
        }
        .social-btn .btn-github {
            background: #333;
        }
        .social-btn .btn-google {
            background: #e54;
        }

        .btn-login {
            background: #6a5;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h2>Sign In</h2>
    </div>
    <form name="login-form" method="post" action="login">
        <div class="form-group">
            <div class="input-group flex-nowrap">
                <input type="text" class="form-control" placeholder="Username" aria-describedby="addon-wrapping"
                       id="username" name="username">
            </div>
        </div>
        <div class="form-group">
            <div class="input-group flex-nowrap">
                <input type="password" class="form-control" placeholder="Password" aria-describedby="addon-wrapping"
                       id="password" name="password">
            </div>
        </div>
        <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="remember-me">
            <label class="form-check-label" for="remember-me">Remember me</label>
            <a class="forgot" href="#">forgot?</a>
        </div>
        <button class="btn-login" type="submit" id="btn-login">Sign In</button>
        <p class="form-text text-white text-center">
            Not yet a member? <a class="text-warning" href="signup.html" style="text-decoration: underline">Sign up</a>
        </p>
        <div class="sign-in">
            <p class="text-center">Login with your social media account</p>
            <div class="text-center social-btn">
                <a href="<%=new FBConnection().getFBAuthUrl()%>" class="btn btn-facebook"><i class="fab fa-facebook-f"></i>&nbsp; Facebook</a>
                <!-- <a href="#" class="btn btn-github"><i class="fab fa-github"></i>&nbsp; Github</a> -->
                <a href="<%=request.getContextPath()%>/logingg" class="btn btn-google"><i class="fab fa-google"></i>&nbsp; Google</a>
            </div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript">

</script>
</html>