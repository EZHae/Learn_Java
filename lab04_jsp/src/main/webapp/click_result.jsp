<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL</title>
	</head>
	<body>
        <main>
            <%@ include file="header.jspf"%>
    
            <h1>클릭 결과 페이지</h1>
    
            <h2>
                안녕하세요,
                <span style="color: ${ param.color }">${ param.username }!</span>
                <%-- 
                    요청 URL의 질의 문자열(query string)에 포함된 요청 파라미터 값을 찾는 방법.
                        (1) JSP expression: <%= request.getParameter("요청 파라미터이름") %>
                        (2) EL: ${ param.요청 파라미터이름 }
                --%>
            </h2>
            
            <%-- JSTL 조건문 --%>  
            <%-- 요청 파라미터 username의 값이 admin인 경우, guest인 경우, 그 외인 경우 --%>
            <h2>scriptlet 사용</h2>
            <% if(request.getParameter("username").equals("admin")) { %>
                <h3 style="color: crimson">관리자 페이지</h3>
            <% } else if (request.getParameter("username").equals("guest")) { %>
                <h3 style="color: dodgerblue">손님 페이지</h3>
            <% } else { %>
                <h3 style="color: green">일반 사용자 페이지</h3>
            <% } %>
            <br>
            
            <h2>JSTL 사용</h2>
            <%-- JSTL 조건문 choose/when/otherwise --%>
            <h3>JSTL-choose/when/otherwise</h3>
            <c:choose>
                <c:when test="${ param.username eq 'admin' }">
                    <h3 style="color: crimson">관리자 페이지</h3>
                </c:when>
                <c:when test="${ param.username == 'guest' }">
                    <h3 style="color: dodgerblue">손님 페이지</h3>
                </c:when>
                <c:otherwise>
                    <h3 style="color: green">일반 사용자 페이지</h3>
                </c:otherwise>
            </c:choose>
            <%-- JSTL 조건문 if. JSTL의 if는 else태그가 없음 --%>
            <h3>JSTL-if</h3>
            <c:if test="${ param.username eq 'admin' }">
                <h3 style="color: crimson">관리자 페이지</h3>
            </c:if>
            <c:if test="${ param.username ne 'admin' }">
                <h3 style="color: green">일반 사용자 페이지</h3>
            </c:if>
        </main>
	</body>
</html>
