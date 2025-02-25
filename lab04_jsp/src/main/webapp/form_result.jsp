<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
<%--
    JSP 내장 객체: jsp 파일이 java 소스 코드로 변환될 때 생성되는 _jspService(request, response) 메서드
    안에서 선언된 지역변수(와 파라미터)들
    (*주의*) scripitlet 안에서 냉장 객체와 같은 이름으로 지역 변수를 선언할 수 없음.
        o. request: 클라이언트에서 서버로 보내는 요청에 대한 정보와 관련 메서드들을 가지고 있는 객체.
            - request.getParameter(), getRequestDispatcher(), getAttribute(), setAttribute(), ...
        o. response: WAS에서 응답을 만들 때 필요한 정보와 관련 메서드들을 가지고 있는 객체.
            - response.setContentType(), response.sendRedirect(), ...
        o. out: JSPWriter객체. HTML 코드 작성 기능을 가지고 있는 객체.
            - out.write(), out.print(), out.println(), ...
        o. pageContext: JSP 페이지가 유지되는 동안 정보를 저장하기 위한 객체.
            - getAttribute(), setAttribute(), ...
        o. session: 세션이 유지되는 동안 정보를 저장하기 위한 객체. (예) 로그인 상태 유지.
            - getAttribute(), setAttribute(), ...
        o. application: 웹 애플리케이션이 동작 중에(WAS가 종료될 때 까지) 유지되는 정보를 저장하기 위한 객체.
            - getAttribute(), setAttribute(), ...
        o. config: 서블릿 환경 설정 정보를 저장하기 위한 객체.
        
    내장 객체의 정보가 유지되는 범위
        pageContext < request < session < application
            - pageContext: 한 개의 페이지에서만 유지되는 정보
            - request: 한 개 이상의 페이지에서 유지되는 정보
            - session: 브라우저에 계속 유지되는 정보 
            - application: 서버에 계속 유지되는 정보
--%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP</title>
        <style>
            <%
            String color = request.getParameter("color");
            String textColor = null;
            switch (color) {
            case "R":
                textColor = "crimson";
                break;
            case "B":
                textColor = "dodgerblue";
                break;
            case "G":
                textColor = "mediumSeaGreen"; 
                break;
            default:
                textColor = "black";
            }
            %>
            span#username {
                color: <%= textColor %>;
            }
        </style>
	</head>
	<body>
		<%@ include file="header.jspf" %>
        
        <main>
            <h1>폼(form) 양식 제출 결과</h1>
            
            <% /* 클라이언트에서 전송한 요청 파라미터의 값을 찾는 방법
            클라이언트에서 submit 받은 데이터를 가지고 있는 객체: request
            request.getParameter(arg): request 객체 속성 중 name의 value를 얻음. */ 
            String username = request.getParameter("username");
            %>
            
            <h2>안녕하세요, <span id="username"><%= username %></span>!</h2>
            
            <% if (username.equals("admin")) { %>
                <h3>관리자 페이지</h3>
            <% } else { %>
                <h3>일반 사용자 페이지</h3>
            <% } %>
            
            <br><br>
            <div>
                <h2>제출 결과</h2>
                <span style="font-weight: bold;">username: </span>
                <span><%= username %></span>
                <br>
                <span style="font-weight: bold;">color: </span>
                <span><%= color %></span>
            </div>
        </main>
	</body>
</html>
