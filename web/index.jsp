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
    <body>
        <h1>Bienvenido  <%out.print(user);%></h1>
        <h3>Crear base de datos</h3>
        Nombre <input type="text" id="dbname" name="dbname"/>
        Usuario <input type="text" id="user" name="user"/>
        Password <input type="text" id="pass" name="pass"/>
        <input type="button" value="crear" />
        <div id="MyDiv"></div>
        <h3>Mis bases de datos</h3>
        <table>
            
        </table>
    </body>
</html>
