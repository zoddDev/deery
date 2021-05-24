<%--
  Created by IntelliJ IDEA.
  User: josie
  Date: 23/05/2021
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="border-bottom">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="background-color: #2E3440 !important;">
        <div class="container-fluid">
            <div class="d-flex justify-content-start ms-2">
                <img width="45" height="45"
                     src="https://icon-library.com/images/paint-brush-icon-png/paint-brush-icon-png-13.jpg" style="-webkit-filter: invert(1); filter: brightness(100);" />
                <a class="navbar-brand ms-3" href="/">DEERY</a>
            </div>
            <div class="d-flex justify-content-end">
                <div class="collapse navbar-collapse" id="navbarText">
                    <ul class="navbar-nav mb-2 mb-lg-0">
                        <li class="nav-item me-4">
                            <a class="nav-link <%= request.getAttribute("currentpage") != null && request.getAttribute("currentpage").equals("index")  ? "link-active" : ""  %>" href="/">Start</a>
                        </li>
                        <li class="nav-item me-4">
                            <a class="nav-link <%= request.getAttribute("currentpage") != null && request.getAttribute("currentpage").equals("artworks")  ? "link-active" : ""  %>" href="/artworks">Artworks</a>
                        </li>
                        <li class="nav-item me-5">
                            <a class="nav-link <%= request.getAttribute("currentpage") != null && request.getAttribute("currentpage").equals("characters")  ? "link-active" : ""  %>" aria-current="page" href="./index.html">Characters</a>
                        </li>
                    </ul>
                </div>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                        aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </div>
    </nav>
</div>