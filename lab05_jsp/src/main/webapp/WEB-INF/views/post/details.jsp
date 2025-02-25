<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>JSP2</title>
        
        <!-- Bootstrap CSS 링크 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
                rel="stylesheet" 
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
                crossorigin="anonymous">
	</head>
	<body>
		<div class="container-fluid">
            <c:set value="상세 보기" var="pageTitle"></c:set>
            <%@ include file="../fragments/header.jspf" %>
        </div>
         
        <main class="m-2">
            <div class="card">
                <div class="card-header">
                    <div class="position-relative">
                        <div><h2>${ post.title }</h2></div>
                        <div><h5>${ post.author }</h5></div>
                        <div><span>작성시간: ${ post.createdTime }</span></div>
                        <div><span>수정시간: ${ post.modifiedTime }</span></div>
                        <div class="position-absolute top-0 end-0">
                            <p>글 번호: ${ post.id }</p>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <p class="lh-lg" style="white-space: pre">${ post.content }</p>
                </div>
                <c:if test="${ post.author eq signedInUser }">
                    <div class="card-footer">
                        <div class="d-flex justify-content-center">
                            <c:url value="/post/modify" var="postModifyPage">
                                <c:param name="id" value="${ post.id }" />
                            </c:url>
                            <a class="btn btn-outline-success" href="${ postModifyPage }">수정하기</a>
                        </div>
                    </div>
                </c:if>
            </div>
        </main>
         
        <!-- Bootstrap JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
	</body>
</html>