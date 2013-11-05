<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WepaHT</title>
    </head>
    <body>
        <p><a href="login">Log in</a> to view your favourite stops. Not registered? Register <a href="register">here</a></p>
        <h1>Bus stop timetables</h1>
        <form action="search" method="POST">
            <label> Search: <input type="text" name="busStop" id="busStop"/></label>
            <label> <input type="submit" value="Search"/></label>
        </form>
        
        <p>${userCreated}</p>
        
        <p>${resultText}</p>
        
        <c:forEach var="stop" items="${stops}">
            <ol><a href="stops/${stop.code}">${stop.name} ${stop.code}</a></ol>
        </c:forEach>
        
            
            <style>
                #userStops {
                    position: absolute;
                    left: 40%;
                }
            </style>
            <div id="userStops">
                
                <c:forEach var="stop" items="${stopList}">
                    <c:forEach var="departure" items="${stop.departures}">
                        <ul>Line: ${departure.lineCode} Time: ${departure.passingTime}</ul>
                    </c:forEach>
                        <br>
                </c:forEach>
                        
                <p>${stopCode}</p>
                <c:if test="${not empty user}">${user.name} ${user.id}</c:if>          
                <c:if test="${empty user}">You haven't logged in!</c:if>
 
            </div>
    </body>
</html>


