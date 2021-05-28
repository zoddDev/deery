<%@ page import="es.spring.deery.entity.Artwork" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="es.spring.deery.entity.OC" %>
<%@ page import="es.spring.deery.model.Autentication" %>
<%--
  Created by IntelliJ IDEA.
  User: josie
  Date: 23/05/2021
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deery: Login</title>
    <link rel="icon" href="images/paint-brush-icon.png">
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <script src="https://kit.fontawesome.com/3964857799.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="components/main.css">
    <link rel="stylesheet" href="components/bootstrap.css">
</head>
<body class="bg-nord-blurred">
<%
    request.setAttribute("currentpage", "login");
%>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container">
        <div class="row m-5 justify-content-center text-center bg-light shadow rounded">
            <h1 class="text-lg-center text-dark fw-bold py-2" style="font-size: 300%;">Log in</h1>
        </div>

        <div class="row m-5 justify-content-center text-center bg-light shadow p-4 rounded">
            <div class="col-md-6 mx-5">
                <form action="login" method="POST">
                    <div class="my-4">
                        <label class="form-label lead fw-bold text-dark">Username</label>
                        <input type="text" class="form-control" name="username" maxlength="20" required>
                    </div>
                    <div class="my-3">
                        <label class="form-label lead fw-bold text-dark">Password</label>
                        <input type="password" class="form-control" name="password" maxlength="20" required>
                    </div>
                    <input type="submit" class="btn btn-pink text-light my-3 fw-bold w-25" value="Log in">
                    <a href="/" class="mx-3 text-primary">Cancel</a>
                </form>
            </div>
            <div class="col-md-4 mx-5">
                <img src="images/anime-nord.png" class="w-100 rounded shadow-sm">
            </div>
        </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>
