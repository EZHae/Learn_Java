<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Session</title>
	</head>
	<body>
		<%@ include file="../../header.jspf" %>
        
        <main>
            <h1>Session</h1>
            <h2>안녕하세요, ${ nickname }!</h2>
            <%-- ${ nickname } 값 찾는 순서:
                 (1) ${pageScope.nickname} = <%= pageContext.getAttribute("nickname") %> 
                 -> (2) ${requestScope.nickname} = <%= request.getAttribute("nickname") %>
                 -> (3) ${sessionScope.nickname} = <%= session.getAttribute("nickname") %> 
                 -> (4) ${applicationScope.nickname} = <%= application.getAttribute("nickname") %>
                 -> null 
            --%>
        </main>
	</body>
</html>