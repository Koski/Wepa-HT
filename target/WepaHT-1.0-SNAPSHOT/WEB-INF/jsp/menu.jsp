<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WepaHT</title>
    </head>
    <body>
        <c:if test="${empty user}">
            <p><a href="login">Log in</a> to view your favourite stops. Not registered? Register <a href="register">here</a></p>
        </c:if>
        <h1>Bus stop timetables</h1>
        <p>Search with name or number of a stop</p>
        <form action="search" method="POST">
            <label> Search: <input type="text" name="busStop" id="busStop"/></label>
            <label> <input type="submit" value="Search"/></label>
        </form>
        
        
        <p>${resultText}</p>
        
        <c:forEach var="stop" items="${stops}">
            <ol><a href="stops/${stop.code}">${stop.name} ${stop.code} </a></ol>
        </c:forEach>
                 
            <style>
                #userStops {
                    position: fixed;
                    left: 35%;
                    top: 15px;
                    font-size: large;
                }
                #userLogged {
                    position: fixed;
                    top: 15px;
                    left: 20%
                }
                html{
                    list-style: none;
                }
            </style>
            <div id="userLogged">
                <c:if test="${not empty user}"><p>Logged in as ${user.name} (<a href="logout">logout</a>)</p></c:if>
            </div>
            <div id="userStops">
                <c:if test="${not empty user}"><h3>My favourite stops:</h3></c:if>              
                <c:forEach var="stop" items="${stopList}">
                    <p>${stop.name} (${stop.code}) (<a href="remove/${stop.code}">Remove from favourites)</a></p>
                    <c:forEach var="departure" items="${stop.departures}">
                        <li>Line: ${departure.lineCode} Time: ${departure.passingTime} (${departure.minutesUntillPassesStop}min)</li>
                    </c:forEach>
                </c:forEach>

            </div>
    </body>
</html>


