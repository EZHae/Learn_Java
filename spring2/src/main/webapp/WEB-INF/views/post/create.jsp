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
            <c:set var="pageTitle" value="글 작성" />
            <%@ include file="../fragments/header.jspf" %>
        </div>
        
        <main class="m-2">
            <div class="card">
                <div class="card-header">
                    <h3>글 작성</h3>
                </div>
                <div class="card-body">
                    <c:url var="postCreatePage" value="/post/create" />
                    <form method="post" action="${postCreatePage}" id="create">
                        <div class="mt-2">
                            <input class="form-control" type="text" name="title" placeholder="제목을 입력하세요." required autofocus>
                        </div>
                        <div class="mt-2">
                            <textarea class="form-control" rows="10" name="content" wrap="hard" placeholder="내용을 입력하세요." required></textarea>
                        </div>
                        <div class="mt-2">
                            <input class="d-none" type="text" name="author" value="${signedInUser}">
                        </div>
                    </form>
                </div>
                <div class="card-footer">
                    <div class="mt-2 d-flex justify-content-end">
                        <input class="btn btn-outline-success me-2" type="submit" value="등록" form="create">
                        <input class="btn btn-outline-danger" type="reset" value="취소">
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