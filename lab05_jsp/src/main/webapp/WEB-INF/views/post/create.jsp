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
            <c:set value="새 글 작성" var="pageTitle"></c:set>
            <%@ include file="../fragments/header.jspf" %>
        </div>
        
        <main class="m-2">
            <div class="card">
                <div class="card-header">
                    <h1>새 블로그 작성</h1>
                </div>
                <div class="card-body">
                    <c:url value="/post/create" var="postCreatePage"></c:url>
                    <form action="${ postCreatePage }" method="post">
                        <div class="mt-2">
                            <input type="text" name="title" placeholder="제목" required autofocus 
                                class="form-control">
                        </div>
                        <div class="mt-2">
                            <textarea rows="10" name="content" wrap="hard" placeholder="내용" required
                                class="form-control"></textarea>
                        </div>
                        <div class="d-none">
                            <input type="text" name="author" value="${ signedInUser }" readonly
                                class="form-control">
                        </div>
                        <div class="mt-2 d-flex justify-content-end">
                            <input type="reset" value="취소"
                                class="me-2 btn btn-outline-danger">
                            <input type="submit" value="확인"
                                class="btn btn-outline-success">
                        </div>
                    </form>
                </div>
            </div>
        </main>
        
        <!-- Bootstrap JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
	</body>
</html>