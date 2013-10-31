<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registeration</title>
    </head>
    <body>
        <h1>Registeration</h1>
        
        <form:form commandName="user" action="create" method="POST">
            Username: <form:input path="name"/><form:errors path="name"/><br>
            Password: <form:input type="password" path="password"/><form:errors path="password"/><br>
            <input type="submit" value="Create"/>
        </form:form>
    </body>
    
</html>