<%-- 
    Document   : login
    Created on : 13/05/2015, 04:16:17 PM
    Author     : brb25
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <input type="text" name="user"/>
    <input type="password" name="pass"/>
    <input type="button" value="Entrar" onclick="callRESTfulWebService('MyDiv','POST',
                'http://localhost:8080/ProyectoFinalSD/webresources/login','<user><name>POLO</name><pass>POLO</pass></user>')" />
     <div id="MyDiv"></div>
    </head>
    <body>
        <h1>Hello World!</h1>
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
    ajaxRequest.setRequestHeader("Content-Type", "application/xml");
    ajaxRequest.send(msg);
}
</script>