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
            <c:set value="Sign In" var="pageTitle"></c:set>
            <%@ include file="../fragments/header.jspf" %>
        </div>
        
        <main class="container w-25 mt-2">
            <div class="card bg-secondary bg-opacity-75">
                <div class="card-header">
                    <h3>로그인</h3>
                </div>
                <div class="card-body bg-secondary">
                    <form method="post">
                        <div class="mt-2">
                            <input class="form-control" type="text" id="username" name="username" 
                                    placeholder="아이디" required autofocus>
                        </div>
                        <div class="mt-2">
                            <input class="form-control" type="password" id="password" name="password" 
                                    placeholder="비밀번호" required>
                        </div>
                        <div class="d-none">
                            <input name="target" value="${ param.target }" readonly>
                        </div>
                        <!-- 로그인 실패 후에 다시 로그인 페이지로 이동한 경우 -->
                        <c:if test="${not empty param.result && param.result eq 'f' }">
                            <div class="text-danger mt-2">
                                아이디와 패스워드를 확인하세요.
                            </div>
                        </c:if>
                        <div class="mt-2">
                            <input class="form-control btn btn-success" type="submit" value="로그인">
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