<%@ page import="es.spring.deery.entity.Artwork" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="es.spring.deery.model.Images" %>
<%@ page import="es.spring.deery.entity.ArtworkOcs" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="es.spring.deery.entity.OC" %>
<%@ page import="es.spring.deery.model.Autentication" %>
<%@ page import="java.util.*" %>
<%@ page import="es.spring.deery.entity.Comment" %>
<%--
  Created by IntelliJ IDEA.
  User: josie
  Date: 24/05/2021
  Time: 11:03
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
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"--%>
    <%--          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">--%>
    <script src="https://kit.fontawesome.com/3964857799.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="components/main.css">
    <link rel="stylesheet" href="components/bootstrap.css">
</head>
<body class="bg-nord-blurred">
<%
    request.setAttribute("currentpage", "artworks");

    Artwork a = (Artwork) request.getAttribute("artwork");
    List<OC> ocs = (List<OC>) request.getAttribute("ocs");
    List<Comment> comments = (List<Comment>) request.getAttribute("comments");
    List<OC> artworkOCs = null;
    BufferedImage img = null;

    artworkOCs = a.getArtworkOcsById().stream().map(ArtworkOcs::getOriginalCharacterByOriginalCharacterId).collect(Collectors.toList());

    // Compare by OC's name
    Collections.sort(artworkOCs, new Comparator<OC>() {
        @Override
        public int compare(OC o1, OC o2) {
            return o1.getName().compareTo(o2.getName());
        }
    });

    img = a.getImg() == null ? null : Images.toImage(a.getImg());
%>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container my-5 text-dark">
    <div class="row mt-5 justify-content-center px-2 g-7 gy-4">

        <div class="d-flex justify-content-center py-2 bg-light shadow-lg my-0 rounded">
            <h1 class="fw-bold"><%= a.getTitle() %>
            </h1>
        </div>

        <!--IMG-->
        <div class="col-md-offset-2 <%= img == null || ((double) img.getHeight() / (double) img.getWidth() >= 0.8) ? "col-sm-4" : "col-sm-8" %>">
            <label class="bg-light p-2 mt-2 shadow-lg rounded">
                <img src="<%= img != null ? "data:image/jpg;base64, " + Base64.getEncoder().encodeToString(a.getImg()) : "images/upload.svg" %>"
                     class="img-fluid mx-auto d-block post-display-horizontal rounded" id="img">
            </label>
        </div>

        <!--FORM FIELDS-->
        <div class="col-sm-8 justify-content-center">
            <%
                if (Autentication.isOwner(request, a) || (a.getDescription() != null && !a.getDescription().isEmpty()) || (artworkOCs != null && !artworkOCs.isEmpty())) {
            %>
            <div class="mt-2 p-4 w-100 bg-light shadow-lg rounded">
                <%
                    if (a.getDescription() != null && !a.getDescription().isEmpty()) {
                %>
                <label class="form-label fw-bold">Description</label>
                <p class="text-dark"><%= a.getDescription() %>
                </p>
                <%
                    }
                %>

                <%
                    if (artworkOCs != null && !artworkOCs.isEmpty()) {
                %>
                <label class="form-label fw-bold">Characters</label>
                <ul class="list-group bg-light">
                    <%
                        for (OC oc : artworkOCs) {
                    %>
                    <li class="list-group-item"><a href="characters-display?id=<%= oc.getId() %>"
                                                   class="text-primary fw-bold undecorated"><%=oc.getName()%>
                    </a></li>

                    <%
                        }
                    %>
                </ul>
                <%
                    }
                %>

                <%
                    if (Autentication.isOwner(request, a)) {
                %>
                <div class="row justify-content-center <%= (a.getDescription() != null && !a.getDescription().isEmpty()) || (artworkOCs != null && !artworkOCs.isEmpty()) ? "mt-lg-5" : "" %>">
                    <a href="artworks-edit?id=<%=a.getId()%>"
                       class="btn btn-info text-light w-50 mx-3 fw-bold">Edit</a>
                </div>

                <%
                    }
                %>
            </div>
            <%
                }
            %>

            <%--COMMENTS--%>
            <div class="mt-2 p-4 w-100 bg-light shadow-lg rounded">
                <label class="form-label fw-bold">Comments</label>
                <%
                    if (comments != null && !comments.isEmpty()) {
                %>
                <ul class="list-group bg-light">
                    <%
                        for (Comment c : comments) {
                    %>
                    <li class="list-group-item">
                        <p class="text-dark fw-bold"><%= c.getUserbdByUserbdId() == null ? "Anonymous:" : c.getUserbdByUserbdId().getUsername() + ":"%>
                        </p>
                        <p class="text-dark lead" style="font-size: 100%"><%=c.getText()%>
                        </p>
                    </li>
                    <%
                        }
                    %>
                </ul>
                <div class="separator"></div>
                <%
                    }
                %>

                <form action="artworks-comment" method="POST" class="col-sm-8 <%= comments.isEmpty() ? "" : "mt-5" %>">
                    <input type="text" name="id"
                           value="<%= a.getId() %>"
                           hidden>
                    <input type="text" name="user-id"
                           value="<%= Autentication.isLogged(request) ? Autentication.getLoggedUser(request).getId() : "" %>"
                           hidden>
                    <input type="text" class="form-control" name="comment"
                           placeholder="<%= Autentication.isLogged(request) ? "(Comment as " + Autentication.getLoggedUser(request).getUsername() + ")" : "(Comment anonymously)" %>">
                    <input type="submit" class="btn btn-primary text-light fw-bold mt-3 w-25" value="Send">
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>
