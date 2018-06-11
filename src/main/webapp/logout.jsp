<%-- 
    Document   : logout
    Created on : Apr 29, 2017, 11:44:05 AM
    Author     : sidde
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <form action="<%
              request.getContextPath();
              %>">
            <input type="submit" value="login"/>
        </form>
        <% 
            session.invalidate();
        %>
    </body>
</html>
