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
            <c:set var="pageTitle" value="로그인" />
            <%@ include file="../fragments/header.jspf" %>
            
            <main>
                <div class="mt-2 card card-body">
                    <c:if test="${not empty param.result and param.result eq 'f'}">
                        <div class="text-danger">아이디와 비밀번호를 확인하세요.</div>
                    </c:if> 
                    <form method="post">
                        <div class="mt-2">
                            <input type="text" class="form-control" id="username" name="username" 
                                placeholder="아이디" required autofocus>
                        </div>
                        <div class="mt-2">
                            <input type="password" class="form-control" id="password" name="password"
                                placeholder="비밀번호" required>
                        </div>
                        
                        <div class="mt-2">
                            <input type="submit" class="form-control btn btn-outline-primary" id="btnSignUp" value="로그인">
                        </div>
                    </form>
                </div>
            </main>
        </div>
        
        <!-- Bootstrap JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
	</body>
</html>