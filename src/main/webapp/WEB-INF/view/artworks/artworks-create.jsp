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

    Artwork a = (Artwork) request.getAttribute("artwork");
    boolean edit = a != null;

    List<OC> ocs = (List<OC>) request.getAttribute("ocs");

    List<OC> artworkOCs = null;
    BufferedImage img = null;

    if (edit) {
        artworkOCs = a.getArtworkOcsById().stream().map(ArtworkOcs::getOriginalCharacterByOriginalcharacterId).collect(Collectors.toList());

        // Compare by OC's name
        Collections.sort(artworkOCs, new Comparator<OC>() {
            @Override
            public int compare(OC o1, OC o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        img = Images.toImage(a.getImg());
    }
%>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container bg-light p-2 my-5 shadow">
    <form action="artworks-save" method="POST" enctype="multipart/form-data">
        <div class="d-flex justify-content-center py-2">
            <h1><%= edit ? "Editing: " + a.getTitle() : "New Artwork"%></h1>
        </div>
        <div class="row mt-5 justify-content-center px-3">
            <div class=<%= !edit || ((double) img.getHeight() / (double) img.getWidth() >= 0.8) ? "col-md-4" : "row" %>>
                <img src="data:image/jpg;base64, <%= edit ? Base64.getEncoder().encodeToString(a.getImg()) : "src/main/resources/static/images/Interrogation.png" %>" class="img-fluid mx-auto d-block post-display-horizontal" id="img">
            </div>
            <div class="d-flex p-2 justify-content-center <%= !edit || ((double) img.getHeight() / (double) img.getWidth() >= 0.8) ? "col-md" : "my-4" %>">
                <div class="w-75">
                    <input type="text" hidden class="form-control" name="id" value="<%= edit ? a.getId() : "" %>">
                    <label class="form-label">Title *</label>
                    <input type="text" class="form-control" name="title" value="<%= edit ? a.getTitle() : "" %>" required><br/>

                    <label class="form-label">Description</label>
                    <input type="text" class="form-control" name="description" value="<%= edit ? a.getDescription() : "" %>"><br/>

                    <label class="form-label"><%= edit ? "File (Leave blank to keep the current image)" : "File *" %></label>
                    <input type="file" class="form-control" name="file" accept="image/png, image/jpeg" id="imgInp" <%= edit ? "" : "required" %>><br/>

                    <button type="button" class="btn btn-outline-primary w-100" data-bs-toggle="modal" data-bs-target="#openCharacterDialog">Characters</button>
                    <div class="row my-5 justify-content-center">
                    <%
                        if (edit) {
                        %>
                            <a href="artworks-create" <%= edit ? "data-bs-toggle=\"modal\" data-bs-target=\"#openEditDialog\"" : "" %> class="btn btn-success w-50 mx-3"><%= edit ? "Confirm changes" : "Create" %></a>
                            <a href="artworks-create" class="btn btn-danger w-25" data-bs-toggle="modal" data-bs-target="#openDeleteDialog">Delete</a>
                        <%
                        } else {
                        %>
                            <input type="submit" class="btn btn-success" value="Create">
                        <%
                        }
                    %>
                    </div>

                    <!--CHARACTERS MODAL-->
                    <div class="modal" id="openCharacterDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <h3> Characters ✨</h3>

                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>

                                <div class="modal-body">
                                        <%
                                            for (OC oc : ocs) {
                                            %>
                                            <div class="form-check">
                                                <input type="checkbox" class="form-check-input mt-2" name="ocs-in-artwork" value="<%= oc.getId() %>" <%= edit && artworkOCs.contains(oc) ? "checked" : "" %> />
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

                                <div class="modal-header">
                                    <h3> Confirm changes ✔️ </h3>

                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>

                                <div class="modal-body">
                                    <div class="row d-flex text-center">
                                        <p class="lead my-5">Proceed with changes?</p>
                                    </div>
                                    <div class="center-block text-center">
                                        <input type="submit" class="btn btn-success" value="Apply changes">
                                        <a href="" class="mx-3" data-bs-dismiss="modal" aria-label="Close">Cancel</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--DELETE MODAL-->
                    <div class="modal" id="openDeleteDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <h3> Confirm deletion ❌ </h3>

                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>

                                <div class="modal-body">
                                    <p class="p-3 text-danger">WARNING: Once you click the delete button you won't be able to recover your artwork.</p>
                                    <div class="row d-flex text-center">
                                        <p class="lead my-5">Proceed with deletion?</p>
                                    </div>
                                    <div class="center-block text-center">
                                        <a href="artworks-delete?id=<%= a.getId() %>" class="btn btn-danger">Yes, I want to delete my artwork</a>
                                        <a href="" class="mx-3" data-bs-dismiss="modal" aria-label="Close">Cancel</a>
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
    imgInp.onchange = evt => {
        const [file] = imgInp.files
        if (file) {
            img.src = URL.createObjectURL(file)
        }
    }
</script>
</html>
