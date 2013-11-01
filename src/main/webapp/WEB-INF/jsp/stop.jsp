<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stop information</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/app/menu">Menu</a>
        <p>${stop.name}</p>
            <c:forEach var="departure" items="${departures}">
                <ul>Line: ${departure.lineCode} Time: ${departure.passingTime} Date: ${departure.date}</ul>
            </c:forEach>
    </body>
</html>
