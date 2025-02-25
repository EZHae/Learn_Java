<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EL</title>
	</head>
	<body>
		<%@ include file="header.jspf" %>
        <%-- 확장자가 jspf인 경우, <jsp:include>를 사용하면 최상단 지시문이 html 코드로 삽입되기 때문에
             <jsp:include>를 사용하지 말기 --%>
        
        <main>
            <h1>EL(Expression Language) (표현)식 언어 </h1>
            <%-- EL(Expression Language
                JSP의 expression tag(<%= %>)를 대체할 수 있는 문법.
                EL의 문법: %{ 식 }
                EL의 특징
                    o. 식(expression): 문자열, 변수, 연산식(사칙연산, 비교 연산)
                    o. 지시문(directive) <%@ ... %> 안에서는 사용할 수 없음.
                    o. 선언문(declaration) <%! ... %> 안에서는 사용할 수 없음.
                    o. 스크립트(scriptlet) <% ... %> 안에서는 사용할 수 없음.
                    o. 식(expression) <%= ... %> 안에서는 사용할 수 없음.
                    o. JSP 태그들을 제외한 JSP의 모든 코드 안에서는 언제든지 사용할 수 있음.
                        - HTML 태그의 컨텐트, 속성 값을 설정
                        - CSS 프로퍼티 값을 설정.
                        - HTML 안에 <script></script> 태그에 포함된 JavaScript 코드에서도 사용 가능
                        - 액션 태그, JSTL 안에서 사용 가능.       
            --%>
            <p><%= 1+1 %></p>
            <p>${ 1+1 }</p>
            
            <%-- JSP 내장 객체에 데이터 저장하기 (내장객체명.setAttribute() 
                    o. pageContext: JSP 페이지가 유지되는 동안 데이터(정보) 저장
                    o. request: 요청 객체가 유지되는 동안 데이터 저장
                    o. session: 세션 객체가 유지되는 동안 데이터 저장.
                    o. application: 웹 애플리케이션이 실행되는 동안 데이터 저장.
                    o. 사용 범위: pageContext < request < session < application
            --%>
            <% // 더미 데이터
            pageContext.setAttribute("id", 1);
            request.setAttribute("id", 2);
            session.setAttribute("id", "admin");
            %>
            
            <h2>JSP expression tag를 사용한 정보 읽기</h2>
            <%-- 내장객체명.getAttribute(String) --%>
            <p>
                pageContext: id = <%= pageContext.getAttribute("id") %> <br>
                request: id = <%= request.getAttribute("id") %> <br>
                session: id = <%= session.getAttribute("id") %>
            </p>
            
            <h2>EL(Expression Language)를 사용한 정보 읽기</h2>
            <%--
                EL과 JSP 내장 객체:
                    o. pageScope = pageContext
                    o. requestScope = request
                    o. sessionScope = session
                    o. applicationScope = application
            --%>
            <p>
                pageScope: id = ${ pageScope.id } <br>
                requestScope: id = ${ requestScope.id } <br>
                sessionScope: id = ${ sessionScope.id } <br>
            </p>
            
            <%-- 
                EL ${ attributeName } 문장에서 속성(attribute) 값을 찾는 순서:
                    (1) pageScope --> (2) requestScope --> (3) sessionScope --> (4) applicationScope
            --%>
            <% request.setAttribute("userid", "scott"); %>
            <p>
                id = ${ id } <br>
                userid = ${ userid } <br> <%-- pageScope에서 없으니 못찾고, requestScope에서 찾음 --%>
            </p>
            
            <h2>EL(Expression Language) 연산자</h2>
            <%-- EL 연산자 
                    o. 산술 연산자: +, -, *, /, %(mod)
                        예) x % 2 --> x mod 2
                    o. 비교 연산자: ==(eq), !=(ne), >(gt), >=(ge), <(lt), <=(le)
                        예) x > 0 --> x gt 0
                    o. 논리 연산자: &&(and), ||(or), !(not)
                        예) x + 2 && x * 1 --> x + 2 and x * 1
                    o. 삼항 연산자: (식) ? 값1 : 값2
                    o. empty: null 이거나 빈 문자열("")인 경우 true
                        예) empty x --> x == null || x.equals("")
                            !empty y --> y != null && y ne ""
                - EL은 문자열을 사용할 때 큰 따옴표("")와 작은 따옴표('') 모두 가능.
            --%>
            <% pageContext.setAttribute("number", 1234); %>
            <p>
                ${ number }는 ${ (number % 2 == 0) ? '짝수' : '홀수' } 입니다.
            </p>
            
            <h2>EL empty 연산자</h2>
            <% request.setAttribute("logInUser", "scott"); %>
            <p>
                logInUser = ${ logInUser } <br>
                ${ empty logInUser ? '로그인 하세요.' : '안녕하세요.' }
            </p>
        </main>
	</body>
</html>
