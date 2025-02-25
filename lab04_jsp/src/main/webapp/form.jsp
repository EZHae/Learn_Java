<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP</title>
	</head>
	<body>
		<%@ include file="header.jspf" %>
        
        <main>
            <h1>폼(form) 양식</h1>
            
            <form method="get" action="form_result.jsp">
                <div>
                    <input type="text" name="username" placeholder="사용자 이름 입력" required autofocus>
                </div>
                <div>
                    <select name="color">
                        <option value="R">빨강</option>
                        <option value="B">파랑</option>
                        <option value="G">초록</option>
                    </select>
                </div>
                <div>
                    <input type="submit" value="제출">
                </div>
            </form>
        </main>
	</body>
</html>