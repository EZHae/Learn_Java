<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.fmt" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>MyBlog</title>
        
        <style>
        .line-1 {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
        
        .line-2 {
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
        }
        </style>
        
        <!-- Bootstrap CSS 링크 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
                rel="stylesheet" 
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
                crossorigin="anonymous">
	</head>
	<body class="bg-dark">
        <div class="container-lg">
            <c:set value="Welcome, MyBlog Home!" var="pageTitle"></c:set>
            <%@ include file="./fragments/header.jspf" %>
        </div>
        
        <main class="container-lg mt-2">
            <div class="card bg-secondary bg-opacity-75">
                <div class="card-header">
                    <h3>최신 글</h3>
                </div>
                <div class="card-body bg-dark text-white">
                    <div>
                        <ul class="list-unstyled">
                            <c:forEach var="post" items="${posts}" >
                            <li class="d-flex align-items-start row">
                                <div class="d-inline-block text-center col-3">
                                    
                                    <!--  -->
                                    <c:choose>
                                        <c:when test="${!empty post.thumbnail}">
                                            <c:url var="thumbnailPath" value="upload/images/${post.thumbnail}" />
                                            <img src="${thumbnailPath}" alt="thumbnail" class="round-4"
                                                style="width: 200px; height: 200px;" >
                                        </c:when>
                                        <c:otherwise>
                                            <c:url var="defaultThumbnailPath" value="/static/images/default_thumbnail.png" />
                                            <img src="${defaultThumbnailPath}" alt="thumnail" class="round-4"
                                                style="width: 200px; height: 200px;">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="d-inline-block ms-2 col-8">
                                    <div class="d-inline-block w-100">
                                        <c:url value="/post/details" var="postDetailsPage">
                                            <c:param name="id" value="${post.id}" />
                                        </c:url>
                                        <a href="${postDetailsPage}"
                                            class="link-light link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover">
                                            <div class="w-100">
                                                <h4 class="line-1">${post.title}</h4>
                                            </div>
                                            <div class="w-100">
                                                <p class="line-2">${post.content}</p>
                                            </div>
                                        </a>
                                        <div class="w-100">
                                            <p class="line-1">${post.author}</p>
                                        </div>
                                        <div class="w-100">
                                            <f:parseDate value="${post.createdTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createdTime" />
                                            <f:formatDate value="${createdTime}" pattern="yyyy년 MM월 dd일 HH:mm" type="both"/>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <hr>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </main>
        
        <!-- Bootstrap JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
	</body>
</html>