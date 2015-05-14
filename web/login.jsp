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

    </head>
          <%
        HttpSession sessionsa = request.getSession();
        sessionsa.invalidate();
        %>
    <body>
        <h1>Log In</h1>
        <input id="user" type="text" name="user"/>
        <input id="pass" type="password" name="pass"/>
        <input type="button" value="Entrar" onclick="callRESTfulWebService('MyDiv','POST', 'http://localhost:8080/ProyectoFinalSD/webresources/login')" />
         <div id="MyDiv"></div>
    </body>
</html>

<script>


function callRESTfulWebService(id, method, target) {
    var ajaxRequest;
    var user = document.getElementById("user");
    var pass = document.getElementById("pass");
    var msg = '<user><name>' + user.value + '</name><pass>'+ pass.value  + '</pass></user>';
    if (window.XMLHttpRequest){
        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }
    ajaxRequest.onreadystatechange = function(){
        if (ajaxRequest.readyState==4 && (ajaxRequest.status==200 || ajaxRequest.status==204)){
            xmlDoc=ajaxRequest.responseXML;
            txt="";
            respuesta = xmlDoc.getElementsByTagName("respuesta")[0].childNodes[0].nodeValue;
            mensaje = xmlDoc.getElementsByTagName("mensaje")[0].childNodes[0].nodeValue;
            
            if (respuesta == "no"){
                txt = mensaje;
            }
            else
            {
                window.location = "./index.jsp";
            }
            
 

            document.getElementById(id).innerHTML = txt ;
        }
    }
    
    
    ajaxRequest.open(method, target, true /*async*/);
    ajaxRequest.setRequestHeader("Content-Type", "application/xml");
    ajaxRequest.send(msg);
}
</script>