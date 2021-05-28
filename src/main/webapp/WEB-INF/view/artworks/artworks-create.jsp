<%@ page import="es.spring.deery.entity.Artwork" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="es.spring.deery.model.Images" %>
<%@ page import="es.spring.deery.entity.ArtworkOcs" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="es.spring.deery.entity.OC" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
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
    Artwork a = (Artwork) request.getAttribute("artwork");
    boolean edit = a != null;

    request.setAttribute("currentpage", edit ? "artworks" : "new");

    List<OC> ocs = (List<OC>) request.getAttribute("ocs");

    List<OC> artworkOCs = null;
    BufferedImage img = null;

    if (edit) {
        artworkOCs = a.getArtworkOcsById().stream().map(ArtworkOcs::getOriginalCharacterByOriginalCharacterId).collect(Collectors.toList());

        // Compare by OC's name
        Collections.sort(artworkOCs, new Comparator<OC>() {
            @Override
            public int compare(OC o1, OC o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        img = a.getImg() == null ? null : Images.toImage(a.getImg());
    }
%>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container my-5 text-dark">
    <form action="artworks-save" method="POST" enctype="multipart/form-data">
        <div class="row mt-5 justify-content-center px-2 g-7 gy-4">

            <div class="d-flex justify-content-center py-2 bg-light shadow-lg my-0 rounded">
                <h1 class="fw-bold"><%= edit ? a.getTitle() : "New Artwork"%></h1>
            </div>

            <!--IMG-->
            <div class="col-md-offset-2 <%= !edit || img == null || ((double) img.getHeight() / (double) img.getWidth() >= 0.8) ? "col-sm-4" : "col-sm-8" %>">
                <label class="bg-light p-2 mt-2 shadow-lg rounded" style="cursor: pointer !important;" for="imgInp">
                    <img src="<%= img != null && edit ? "data:image/jpg;base64, " + Base64.getEncoder().encodeToString(a.getImg()) : "images/upload.svg" %>" class="img-fluid mx-auto d-block post-display-horizontal" id="img">
                </label>
            </div>

            <!--FORM FIELDS-->
            <div class="col-sm-8 justify-content-center">
                <div class="mt-2 p-4 w-100 bg-light shadow-lg rounded">
                    <input type="text" hidden class="form-control" name="id" value="<%= edit ? a.getId() : "" %>">
                    <label class="form-label fw-bold">Title *</label>
                    <input type="text" class="form-control" name="title" value="<%= edit ? a.getTitle() : "" %>" required><br/>

                    <label class="form-label fw-bold">Description</label>
                    <input type="text" class="form-control" name="description" value="<%= edit ? a.getDescription() : "" %>"><br/>

                    <label class="form-label fw-bold"><%= edit ? "File (Leave blank to keep the current image)" : "File *" %></label>
                    <input type="file" class="form-control" name="file" accept="image/png, image/jpeg" id="imgInp" <%= edit ? "" : "required" %>><br/>

                    <button type="button" class="btn btn-outline-primary w-100 fw-bold" data-bs-toggle="modal" data-bs-target="#openCharacterDialog">Characters</button>
                    <div class="row my-5 justify-content-center">
                    <%
                        if (edit) {
                        %>
<%--                            <a href="artworks-create" data-bs-toggle="modal" data-bs-target="#openEditDialog" class="btn btn-success text-light w-50 mx-3 fw-bold">Confirm</a>--%>
                        <input type="submit" class="btn btn-success text-light fw-bold" value="Confirm">

                        <a href="artworks-create" class="btn btn-danger text-light w-25 fw-bold" data-bs-toggle="modal" data-bs-target="#openDeleteDialog">Delete</a>
                        <%
                        } else {
                        %>
                            <input type="submit" class="btn btn-success text-light fw-bold" value="Create">
                        <%
                        }
                    %>
                    </div>

                    <!--CHARACTERS MODAL-->
                    <div class="modal" id="openCharacterDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <div class="modal-header bg-light">
                                    <h3> Characters ✨ </h3>

                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>

                                <div class="modal-body bg-light">
                                    <input type="checkbox" hidden name="ocs-in-artwork"/>
                                    <%
                                        for (OC oc : ocs) {
                                        %>
                                        <div class="form-check">
                                            <input type="checkbox" class="form-check-input mt-2 bg-dark border-dark" name="ocs-in-artwork" value="<%= oc.getId() %>" <%= edit && artworkOCs.contains(oc) ? "checked" : "" %> />
                                            <label class="form-check-label lead">
                                                <%= oc.getName() %>
                                            </label>
                                        </div>
                                         <%
                                        }
                                    %>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--EDIT MODAL-->
                    <div class="modal" id="openEditDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <div class="modal-header bg-light">
                                    <h3> Confirm changes ✔️ </h3>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>

                                <div class="modal-body bg-light">
                                    <div class="row d-flex text-center">
                                        <p class="lead my-5">Proceed with changes?</p>
                                    </div>
                                    <div class="center-block text-center">
                                        <input type="submit" class="btn btn-success text-light fw-bold" value="Apply changes">
                                        <a href="" class="mx-3 text-primary" data-bs-dismiss="modal" aria-label="Close">Cancel</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--DELETE MODAL-->
                    <div class="modal" id="openDeleteDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <div class="modal-header bg-light">
                                    <h3> Confirm deletion ❌ </h3>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>

                                <div class="modal-body bg-light">
                                    <p class="p-3 text-danger">WARNING: Once you click the delete button you won't be able to recover your artwork.</p>
                                    <div class="row d-flex text-center">
                                        <p class="lead my-5">Proceed with deletion?</p>
                                    </div>
                                    <div class="center-block text-center">
                                        <a href="artworks-delete?id=<%= edit ? a.getId() : null %>" class="btn btn-danger text-light fw-bold">Yes, I want to delete my artwork</a>
                                        <a href="" class="mx-3 text-primary" data-bs-dismiss="modal" aria-label="Close">Cancel</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
<script>
    // Image Preview
    imgInp.onchange = evt => {
        const [file] = imgInp.files
        if (file) {
            img.src = URL.createObjectURL(file)
        }
    }
</script>
</html>
