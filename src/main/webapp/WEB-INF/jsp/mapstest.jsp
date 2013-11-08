<%-- 
    Document   : mapstest
    Created on : 18-Oct-2013, 14:34:59
    Author     : anttkari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <style type="text/css">
            html { height: 100% }
            body { height: 100%; margin: 0; padding: 0 }
            #map-canvas {
                height: 200px;
                width: 200px;
                
            }
        </style>
        <script type="text/javascript"
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDOyID2b5cGxLeSGtSLk1I-fn5R6MJUPo8&sensor=true">
        </script>
        <script type="text/javascript">
            //              Purotie 8, Helsinki
            var map;
            var geocoder;
            
            function codeAddress() {
//                var address = "purotie 8, Helsinki";
                geocoder.geocode( { 'address': address}, function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        map.setCenter(results[0].geometry.location);
                        var marker = new google.maps.Marker({
                            map: map,
                            position: results[0].geometry.location
                        });
                    } else {
                        alert("Geocode was not successful for the following reason: " + status);
                    }
                });
            }
//            -34.397, 150.644
//            24.82324,60.18629
            function initialize(lat, long) {
                geocoder = new google.maps.Geocoder();
                var mapOptions = {
                    center: new google.maps.LatLng(60.18629, 24.82324),
                    zoom: 18,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                map = new google.maps.Map(document.getElementById("map-canvas"),
                mapOptions);
            }
            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
    </head>
    <body>
        <div id="map-canvas"/>
    </body>
</html>
