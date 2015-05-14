<%-- 
    Document   : index
    Created on : 13/05/2015, 06:33:28 PM
    Author     : brb25
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
HttpSession sessionsa = request.getSession();
String user = (String) sessionsa.getAttribute("connected_user");
if (user == null){
   response.sendRedirect("./login.jsp");
}


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="getDatabases();">
        <h1>Bienvenido  <%out.print(user);%></h1>
        <h3>Crear base de datos</h3>
        Nombre <input type="text" id="dbname" name="dbname"/>
        Usuario <input type="text" id="user" name="user"/>
        Password <input type="text" id="pass" name="pass"/>
        <input type="button" value="crear" />
        <div id="MyDiv"></div>
        <h3>Mis bases de datos</h3>
        <table>
            <tr>
                <td><input type="button" value="X"/></td>
                <td><input type="text" size="50" disabled/></td>
                <td><input type="button" value="Editar"/></td>
            </tr>
        </table>
    </body>
</html>


<script>


function getDatabases() {
    var ajaxRequest;
    var userid = <% out.print(sessionsa.getAttribute("connected_user_id")); %>;

    var msg = '<user><id>' + userid + '</id></user>';
    if (window.XMLHttpRequest){
        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }
    ajaxRequest.onreadystatechange = function(){
        if (ajaxRequest.readyState==4 && (ajaxRequest.status==200 || ajaxRequest.status==204)){
            xmlDoc=ajaxRequest.responseXML;
            

            respuesta = xmlDoc.getElementsByTagName("r")[0].childNodes[0].nodeValue;
            alert(respuesta);
           
        }
    }
    
    
    ajaxRequest.open("GET", "http://localhost:8080/ProyectoFinalSD/webresources/databases", true /*async*/);
    ajaxRequest.setRequestHeader("Content-Type", "application/xml");
    ajaxRequest.send();
}
</script>