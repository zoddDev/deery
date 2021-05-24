<%@ page import="es.spring.deery.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: josie
  Date: 23/05/2021
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<head>
    <title>JSP</title>
</head>
<body>

    <h1>ESTO ES EL JSP</h1>

    <ul>
        <%
            for (User u : users) {
                %>
                    <li><%= u.getUsername() + " (" + u.getEmail() + ")" %></li>
                <%
            }
        %>
    </ul>
</body>
</html>
