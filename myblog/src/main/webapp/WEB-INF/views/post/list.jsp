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
        
        <title>My Blog</title>
        
        <style>
        .line-1 {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            word-break: break-all;
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
            <c:set value="Post List" var="pageTitle"></c:set>
            <%@ include file="../fragments/header.jspf" %>
        </div>
		
        <main class="container-lg mt-2">
            <div class="card bg-secondary bg-opacity-75">
                <div class="card-header">
                    <h3>TODO</h3>
                </div>
                <div class="card-body p-0">
                    <table class="table m-0 table-dark table-striped table-hover">
                        <thead>
                            <tr class="text-center">
                                <th class="col-1">번호</th>
                                <th class="col-7">제목</th>
                                <th class="col-2">작성자</th>
                                <th class="col-2">작성시간</th>
                            </tr>
                        </thead>
                        <tbody class="table-group-divider">
                            <c:forEach var="post" items="${posts}">
                                <tr>
                                    <td class="align-middle text-center col-1">${post.id}</td>
                                    <c:url value="/post/details" var="postDetailsPage">
                                        <c:param name="id" value="${post.id}" />
                                    </c:url>
                                    <td class="align-middle col-7" style="max-width: 400px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
                                        <a href="${ postDetailsPage }" 
                                           class="link-light link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover" 
                                           style="display: block; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
                                           ${ post.title }
                                        </a>
                                    </td>
                                    <td class="align-middle col-2 text-center">${post.author}</td>
                                    <td class="align-middle col-2 text-end">
                                        <f:parseDate value="${post.createdTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createdTime" />
                                        <f:formatDate value="${createdTime}" pattern="MM월 dd일 HH:mm" type="both"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="d-flex justify-content-center mt-3">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${totalPage}" var="page">
                        <li class="page-item ${page == currentPage ? 'active' : ''}">
                            <a class="page-link" href="?page=${page}">${page}</a>
                        </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </main>
        
        <!-- Bootstrap JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
	</body>
</html>