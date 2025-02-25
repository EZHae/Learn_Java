<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>Spring 2</title>
        
        <!-- Bootstrap CSS 링크 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
                rel="stylesheet" 
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
                crossorigin="anonymous">
	</head>
    <body>
        <div class="container-fluid">
            <c:set var="pageTitle" value="포스트 수정하기" />
            <%@ include file="../fragments/header.jspf" %>
            
            <main>
                <div class="mt-2 card">
                    <div class="card-body">
                        <form id="modifyForm">
                            <div class="d-none">
                                <label for="id" class="form-label">번호</label>
                                <input id="id" class="form-control" type="text"
                                    name="id" value="${post.id}" readonly />
                            </div>
                            <div class="mt-2">
                                <label for="title" class="form-label">제목</label>
                                <input id="title" class="form-control" type="text"
                                    name="title" value="${post.title}" />
                            </div>
                            <div class="mt-2">
                                <label for="content" class="form-label">내용</label>
                                <textarea id="content" class="form-control" rows="5" 
                                    name="content">${post.content}</textarea>
                            </div>
                        </form>
                    </div>
                    <c:if test="${signedInUser eq post.author}">
                        <div class="card-footer d-flex justify-content-end">
                            <button id="btnDelete" class="me-2 btn btn-outline-danger">삭제</button>
                            <button id="btnUpdate" class="btn btn-outline-success">업데이트</button>
                        </div>
                    </c:if>
                </div>
            </main>
        </div>
        
        <!-- Bootstrap JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
                
        <%-- 자바스크립트 요청 주소 /spring2/js/post-modify.js 
            servlet-context.xml 설정에 의해서 static 폴더 아래에서 js 폴더를 찾음.
        --%>
        <c:url var="postModifyJS" value="/js/post-modify.js" />
        <script src="${postModifyJS}"></script>
	</body>
</html>