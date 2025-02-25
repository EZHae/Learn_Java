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
            <c:set var="pageTitle" value="글 목록" />
            <%@ include file="../fragments/header.jspf" %>
        </div>
        
        <main class="m-2">
            <div class="card">
                <div class="card-header">
                    <c:url var="postSearchPage" value="/post/search" />
                    <form action="${postSearchPage}" method="get">
                        <div class="row">
                            <div class="col-2">
                                <select class="form-control" name="category">
                                    <option value="t">제목</option>
                                    <option value="c">내용</option>
                                    <option value="tc">제목+내용</option>
                                    <option value="a">작성자</option>
                                </select>
                            </div>
                            <div class="col-8">
                                <input class="form-control" type="text" name="keyword" placeholder="검색어 입력" required>
                            </div>
                            <div class="col-2">
                                <input class="form-control btn btn-outline-secondary" type="submit" value="검색">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="card-body">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성 시간</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="post" items="${posts}">
                            <tr>
                                <td>${post.id}</td>
                                <td>
                                    <c:url var="postDetailsPage" value="/post/details">
                                        <c:param name="id" value="${post.id}" />
                                    </c:url>
                                    <a href="${postDetailsPage}">${post.title}</a>
                                </td>
                                <td>${post.author}</td>
                                <td>${post.createdTime}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
        
        <!-- Bootstrap JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
	</body>
</html>