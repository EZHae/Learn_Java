<%@page import="com.itwill.jsp1.model.Contact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Action Tag</title>
        <style>
            div.card {
                border: 1px solid gray;
                border-radius: 8px;
                margin: 8px;
                padding: 8px;
            }
        </style>
	</head>
	<body>
		<%@ include file="header.jspf" %>
        
        <main>
            <h1>JSP Action Tag</h1>
            <%-- JSP Action Tag
                    - scriptlet에서 사용되는 일부 자바 코드들을 HTML 또는 XML과 비슷하게 태그,
                      태그 속성(attribute), 태그 컨텐트로 작성해서 대체하기 위한 문법.
                    - 액션 태그는 대/소문자를 구분 (HTML 태그는 대/소문자를 구분하지 않음)
                    - 액션 태그는 시작 태그와 종료태그가 반드시 있어야 함.
                        o. <jsp:forward>: request.getRequestDispatcher(""),forward(req, resp)
                        o. <jsp:include>: <%@ include file="path" %>와 "비슷"
                        o. <jsp:useBean>: (기본) 생성자 호출.
                        o. <jsp:getProperty>: getter 메서드 호출.
                        o. <jsp:setProperty>: setter 메서드 호출.
            --%>
            <h2>액션 태그를 사용하지 않은 자바 객체 생성</h2>
            <%
            Contact contact1 = new Contact(); // 기본 생성자 호출
            contact1.setId(1);
            contact1.setName("홍길동");
            contact1.setPhone("010-1234-5678");
            contact1.setEmail("hgd@test.com");
            %>
            <div class="card">
                ID: <%= contact1.getId() %> <br>
                NAME: <%= contact1.getName() %> <br>
                PHONE: <%= contact1.getPhone() %> <br>
                EMAIL: <%= contact1.getEmail() %> <br>
            </div>
            
            <h2>액션 태그를 사용한 자바 객체 생성</h2>
            <jsp:useBean id="contact2" class="com.itwill.jsp1.model.Contact"></jsp:useBean>
            <jsp:setProperty property="id" name="contact2" value="2"/>
            <jsp:setProperty property="name" name="contact2" value="오쌤"/>
            <jsp:setProperty property="phone" name="contact2" value="010-0000-0000"/>
            <jsp:setProperty property="email" name="contact2" value="jake@itwill.com"/>
            <div class="card">
                ID: <jsp:getProperty property="id" name="contact2"/> <br>
                NAME: <jsp:getProperty property="name" name="contact2"/> <br>
                PHONE: <jsp:getProperty property="phone" name="contact2"/> <br>
                EMAIL: <jsp:getProperty property="email" name="contact2"/> <br>
            </div>
            <%--
                - <jsp:useBean>은 기본 생성자를 가지고 있는 클래스만 사용할 수 있음.
                - <jsp:setProperty>, <jsp:getProperty>의 속성
                    o. property: get/set으로 접근할 필드명(변수명) 
                    o. name: 접근할 객체의 이름
                    o. value: 할당할 필드의 값
            --%>
            
        </main>
        
        <jsp:include page="footer.jsp"></jsp:include>
            <%-- include 지시문과 액션태그의 차이점
                <%@ include >는 하나의 JSP 파일로 합쳐진 후에, 하나의 java 파일로 변환되고, 컴파일 됨.
                <jsp:include>는 각각의 JSP 파일들이 java 파일로 변환되고, 컴파일 된 후에 하나의 HTML 문서가 작성됨.
            --%>
	</body>
</html>
