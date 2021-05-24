<%@ page import="es.spring.deery.entity.Artwork" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
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
    <title>Title</title>
    <link rel="icon" href="https://victorpastor.com/wp-content/uploads/2020/11/logo.png">
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/3964857799.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="components/main.css">
</head>
<body class="bg-gradient-animated">
<%
    request.setAttribute("currentpage", "artworks");
%>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container">
    <div class="row m-5 justify-content-center text-center">
        <h1 class="text-lg-center" style="font-size: 5rem">DEERY'S ARTWORKS</h1>
    </div>
    <div class="row m-5 justify-content-center">
        <a href="artworks-create" class="btn btn-primary w-25 mx-3">+</a>
    </div>
    <div class="row mt-5">
        <%
            for (Artwork a : (List<Artwork>) request.getAttribute("artworks")) {
                %>
                <div class="col-md-6 col-lg-4 mb-5 p-3">
                    <div class="row justify-content-center">
                        <a href="artworks-edit?id=<%= a.getId() %>">
                            <img src="data:image/jpg;base64, <%= Base64.getEncoder().encodeToString(a.getImg()) %>" class="bg-light img-fluid mx-auto d-block thumb-post p-2 shadow-lg">
                        </a>
                    </div>
                </div>
                <%
            }
        %>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>
