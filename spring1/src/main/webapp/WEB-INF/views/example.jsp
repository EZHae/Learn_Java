<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>Spring 1</title>
        
        <!-- Bootstrap CSS 링크 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
                rel="stylesheet" 
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
                crossorigin="anonymous">
	</head>
	<body>
		<nav>
            <ul>
                <li>
                    <c:url value="/" var="homePage" />
                    <a href="${homePage}">홈페이지</a>
                </li>
            </ul>
        </nav>
        
        <main>
            <h1>Example Page</h1>
            <section>
                <h2>GET 방식 요청</h2>
                <c:url value="/ex1" var="ex1Page" />
                <form method="get" action="${ex1Page}">
                    <input type="text" name="username" placeholder="이름을 입력하세요.">
                    <input type="number" name="age" placeholder="나이를 입력하세요.">
                    <input type="submit" value="제출">
                </form>
            </section>
            
            <section>
                <h2>POST 방식 요청</h2>
                <c:url value="/ex2" var="ex2Page" />
                <form method="post" action="${ex2Page}">
                    <input type="text" name="username" placeholder="이름을 입력하세요.">
                    <input type="number" name="age" placeholder="나이를 입력하세요.">
                    <input type="submit" value="제출">
                </form>
            </section>
        </main>
        
        <!-- Bootstrap JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
	</body>
</html>