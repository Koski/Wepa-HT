<%-- 
    Document   : login
    Created on : 25.10.2013, 15:39:00
    Author     : Antti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <!--<p>you're already logged in, back to </p>-->
        <a href="menu">menu</a>
        <h1>Log in here</h1>
        <form action="login" method="POST">
            <label> Username: <input type="text" name="name" id="name"/></label>
            <label> Password: <input type="password" name="password" id="password"/></label><br>
            <input type="submit" value="Login"/>
        </form>
        <p>${loginError}</p>
    </body>
</html>
