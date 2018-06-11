<%@page import="java.util.Date"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jaspic Test</title>
    </head>
    <body>
        <h1>Jaspic Application</h1>
        
        <%
            out.println("Welcome, "+request.getUserPrincipal().getName());
            out.println("Has role: "+request.isUserInRole("JBossAdmin"));
            out.println("<p>"+new Date()+"</p>");
            
            out.println("<p> Cookie: "+request.getHeader("Cookie"));
            out.println("<p> Set-Cookie: "+response.getHeader("Set-Cookie"));
           
        %>
        <p><a href="logout.jsp">Click Here to Logout</a></p>
    </body>
</html>
