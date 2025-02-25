<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
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
            <c:set value="Post Update" var="pageTitle"></c:set>
            <%@ include file="../fragments/header.jspf" %>
        </div>
        
        <main class="container-lg mt-2">
            <div class="card bg-secondary bg-opacity-75 p-2">
                <form id="modifyForm"><!--  -->
                    <div class="card-body">
                        <div>
                            <input type="text" class="d-none" id="id" name="id" value="${post.id}">
                        </div>
                        <div>
                            <input class="form-control form-control-lg" type="text" id="title" name="title" 
                                    placeholder="제목을 입력하세요." value="${post.title}" required autofocus>
                        </div>
                        <div class="mt-2">
                            <textarea class="form-control" rows="10" id="content" name="content" 
                                    placeholder="내용을 입력하세요." wrap="hard" required>${post.content}
                            </textarea>
                        </div>
                        <div class="mt-2 d-none">
                            <input type="text" id="author" name="author" value="${post.author}" readonly>
                        </div>
                    </div>
                </form>
                <c:if test="${signedInMember eq post.author || signedInMember eq 'admin' }">
                    <div class="card-footer">
                        <div class="d-flex justify-content-center">
                            <button class="btn btn-danger me-2" id="btnDelete">삭제</button>
                            <button class="btn btn-success" id="btnUpdate">업데이트</button>
                            <!-- <button class="btn btn-outline-success" type="submit" form="modifyForm">test</button>  -->
                        </div>
                    </div>
                </c:if>
            </div>
        </main>
        
        <!-- Bootstrap JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
                
        <c:url value="/static/js/post-modify.js" var="postModifyJS"></c:url>
        <script src="${ postModifyJS }"></script>
	</body>
</html>