<%@ page import="es.spring.deery.entity.Artwork" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
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
    <title>Deery</title>
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
    request.setAttribute("currentpage", "artworks");

    List<Artwork> artworks = (List<Artwork>) request.getAttribute("artworks");
    // Compare by date (most recent first)
    Collections.sort(artworks, new Comparator<Artwork>() {
        @Override
        public int compare(Artwork o1, Artwork o2) {
            return Integer.compare(o2.getId(), o1.getId());
        }
    });
%>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container">
    <div class="row m-5 justify-content-center text-center">
        <h1 class="text-lg-center text-light fw-bold" style="font-size: 300%;">DEERY'S ARTWORKS</h1>
    </div>

    <div class="row mt-5 justify-content-center">
        <%
            for (Artwork a : artworks) {
                %>

            <div class="col-md-6 col-lg-4 mb-5 post">
                <div class="p-3">
                    <div class="bg-light rounded p-2 text-center">
                        <a class="undecorated" href="artworks-display?id=<%= a.getId() %>">
                            <div class="image">
                                <img src="data:image/jpg;base64, <%= Base64.getEncoder().encodeToString(a.getImg()) %>" class="img-fluid d-block img-post rounded">
                            </div>
                        </a>
                    </div>
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
