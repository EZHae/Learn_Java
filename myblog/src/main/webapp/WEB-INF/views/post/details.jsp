<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="f" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        
        <!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>My Blog</title>
        
        <!-- Bootstrap CSS 링크 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous">
    </head>
    <body class="bg-dark">
        <div class="container-lg">
            <c:set value="Post Details" var="pageTitle"></c:set>
            <%@ include file="../fragments/header.jspf"%>
        </div>
    
        <main class="container-lg mt-2">
            <div class="card bg-secondary bg-opacity-75">
                <div class="card-header">
                    <div>
                        <div>
                            <h2>${ post.title }</h2>
                        </div>
                        <div>
                            <h5>${ post.author }</h5>
                        </div>
                        <div>
                            <f:parseDate value="${post.createdTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createdTime" />
                            <f:formatDate value="${createdTime}" pattern="작성시간: yyyy년 MM월 dd일 HH:mm" type="both"/>
                        </div>
                        <div>
                            <f:parseDate value="${post.modifiedTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="modifiedTime" />
                            <f:formatDate value="${modifiedTime}" pattern="수정시간: yyyy년 MM월 dd일 HH:mm" type="both"/>
                        </div>
                        <div>
                            <span>글 번호: ${ post.id }</span>
                        </div>
                    </div>
                </div>
                <div class="card-body bg-secondary">
                    <c:if test="${!empty images}">
                        <c:choose>
                            <c:when test="${fn:length(images) == 1}">
                            <c:forEach var="image" items="${images}" >
                                <div>
                                    <c:url var="imagesPath" value="/upload/images/${image.imageName}" />
                                    <img src="${imagesPath}" alt="${image.imageName}"
                                            class="d-block mx-auto" style="max-width:50%; height:auto">
                                </div>
                            </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div id="carouselImage" class="carousel slide">
                                    <div class="carousel-inner w-100">
                                        <c:forEach var="image" items="${images}" varStatus="status" >
                                        <div class="carousel-item ${status.first ? 'active' : ''}">
                                            <c:url var="imagesPath" value="/upload/images/${image.imageName}" />
                                            <img src="${imagesPath}" alt="${image.imageName}"
                                                    class="d-block mx-auto" style="max-width:50%; height:auto">
                                        </div>
                                        </c:forEach>
                                    </div>
                                    <button class="carousel-control-prev" type="button" 
                                            data-bs-target="#carouselImage" data-bs-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Previous</span>
                                    </button>
                                    <button class="carousel-control-next" type="button" 
                                            data-bs-target="#carouselImage" data-bs-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Next</span>
                                    </button>
                                </div>
                            </c:otherwise>
                            </c:choose>
                        </c:if>
                    <div>
                        <p class="lh-lg" style="white-space: pre-wrap">${ post.content }</p>
                    </div>
                    <c:if test="${!empty file}">
                        <hr>
                        <div>
                            <h3>첨부파일</h3>
                            <c:url var="filePath" value="/upload/files/${file.fileName}" />
                            <a href="${filePath}" class="link-dark link-offset-2 
                                link-underline-opacity-0 link-underline-opacity-100-hover" download>
                                ${originalFileName}
                            </a>
                        </div>
                    </c:if>
                </div>
                <c:if test="${signedInMember eq post.author || signedInMember eq 'admin'}">
                    <div class="card-footer">
                        <div class="d-flex justify-content-center">
                            <c:url value="/post/modify" var="postModifyPage">
                                <c:param name="id" value="${ post.id }" />
                            </c:url>
                            <a class="btn btn-success"
                                href="${ postModifyPage }">수정하기</a>
                        </div>
                    </div>
                </c:if>
            </div>
        </main>
    
    
        <!-- Bootstrap JS 링크 -->
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    </body>
</html>