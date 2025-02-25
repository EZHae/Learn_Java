<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP</title>
	</head>
	<body>
        <%--
            지시문(directive)의 종류:
                o. page 지시문 <%@ page attr="value" ... %>
                o. include 지시문 <%@ include file="value" %>
                o. taglib 지시문 <% taglib attr="value" %>
                
            include 지시문(directive)
                - 여러개의 페이지들이 공통된 컨텐트(예: header, footer)를 포함 하는 경우,
                  공통된 컨텐트들을 별도의 jsp/jspf 파일로 작성하고, 필요로 하는 페이지에서 호출
                - include 지시문이 사용된 위치에 jsp/jspf 파일의 내용이 삽입되며, 
                  하나의 java 소스 코드로 변환 후 자바 클래스로 컴파일 됨.
                  (header.java/.class, footer.java/.class, main.java/.class 3개가 아닌 main.java/.class 1개로 컴파일 됨)
                - 확장자는 jspf로 만드는 것을 권장 (jsp fragment 조각)
                  특정 jsp를 이루고 있는 조각을 의미, jsp이여도 무방하지만 권장
         --%>
        <%@ include file="/header.jspf" %>
		<main>
            <h1>메인 페이지</h1>
        </main>
        <%@ include file="footer.jsp" %>
	</body>
</html>