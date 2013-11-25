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
            <li>Line: ${departure.lineCode} Time: ${departure.passingTime} (${departure.minutesUntillPassesStop}min)</li>
        </c:forEach>
        <c:if test="${not empty user}">
            <form action="addStop" method="POST">
                <input type="hidden" name="stopCode" value="${stop.code}">
                <input type="submit" value="Add as a favourite">
            </form>
        </c:if>
        <style type="text/css"> 
            #map-canvas {
                height: 400px;
                width: 400px;
                position: absolute;
                top: 10px;
                left: 400px;
            }
            html {
                list-style: none;
            }
        </style>

        <script type="text/javascript" 
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDOyID2b5cGxLeSGtSLk1I-fn5R6MJUPo8&sensor=true">
        </script>
        <script type="text/javascript">

            function initialize() {
                var mapOptions = {
                    center: new google.maps.LatLng(${stop.latitude},${stop.longitude}),
                    zoom: 16,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                map = new google.maps.Map(document.getElementById("map-canvas"),
                        mapOptions);
            }
            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
        <div id="map-canvas"/>
    </body>
</html>
