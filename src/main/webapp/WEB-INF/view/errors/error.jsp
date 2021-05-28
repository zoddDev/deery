<%--
  Created by IntelliJ IDEA.
  User: josie
  Date: 27/05/2021
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Deery: Error</title>
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
  String returnPage =  (String) request.getAttribute("returnpage");

  String currentPage = (String) request.getAttribute("currentpage");
  request.setAttribute("currentpage", currentPage == null ? "" : currentPage);

  String errorMsg =  (String) request.getAttribute("error");
%>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container bg-light">
  <div class="row m-5 justify-content-center text-center">
    <h1 class="text-lg-center text-dark fw-bold" style="font-size: 300%;">:(</h1>
    <div class="separator"></div>

    <h4 class="text-lg-center text-danger fw-bold"> <%= errorMsg == null || errorMsg.isEmpty() ? "Something went wrong..." : errorMsg %></h4>
  </div>

  <div class="row m-5 justify-content-center text-center">
    <a href=<%= returnPage == null || returnPage.isEmpty() ? "/" : returnPage %>>Return</a>
  </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>
