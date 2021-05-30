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
    request.setAttribute("currentpage", "characters");

    List<OC> ocs = (List<OC>) request.getAttribute("characters");
    // Compare by date (most recent first)
    Collections.sort(ocs, new Comparator<OC>() {
        @Override
        public int compare(OC o1, OC o2) {
            return Integer.compare(o2.getId(), o1.getId());
        }
    });
%>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container">
    <div class="row m-5 justify-content-center text-center">
        <h1 class="text-lg-center text-light fw-bold" style="font-size: 300%;">DEERY'S OCS</h1>
    </div>

    <div class="row mt-5 justify-content-center">
        <%
            for (OC oc : ocs) {
        %>
        <div class="col-md-6 col-lg-4 mb-5">
            <div class="p-3">
                <div class="row text-center bg-light rounded">
                    <a class="undecorated" href="characters-display?id=<%= oc.getId() %>" style="padding-top: 15px;">
                        <img src="<%= oc.getImg() == null ? "images/upload.svg" : "data:image/jpg;base64, " + Base64.getEncoder().encodeToString(oc.getImg()) %>" class="bg-light img-fluid mx-auto d-block thumb-post p-2">
                        <p class="lead fw-bold bg-light undecorated text-dark" style="font-size: 200%"><%= oc.getName() %></p>
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
