<%-- 
    Document   : test_restful.jsp
    Created on : 13/05/2015, 03:22:21 PM
    Author     : brb25
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <button onclick="callRESTfulWebService('MyDiv','GET','http://localhost:8080/ProyectoFinalSD/webresources/users','')" />Test Restful</button>
        <div id="MyDiv"></div>
    </body>
</html>
<script>
function loadNewContent(id, target) {
    var ajaxRequest;
    if (window.XMLHttpRequest){
        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }
    ajaxRequest.onreadystatechange = function(){
        if (ajaxRequest.readyState==4 && ajaxRequest.status==200){
        document.getElementById(id).innerHTML=ajaxRequest.responseText;
        }
    }
    ajaxRequest.open("GET", target, true /*async*/);
    ajaxRequest.send();
}

function callRESTfulWebService(id, method, target, msg) {
    var ajaxRequest;
    if (window.XMLHttpRequest){
        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }
    ajaxRequest.onreadystatechange = function(){
        if (ajaxRequest.readyState==4 && (ajaxRequest.status==200 || ajaxRequest.status==204)){
            document.getElementById(id).innerHTML=ajaxRequest.responseText;
        }
    }
    ajaxRequest.open(method, target, true /*async*/);
    ajaxRequest.setRequestHeader("Content-Type", "text/html");
    ajaxRequest.send(msg);
}
</script>