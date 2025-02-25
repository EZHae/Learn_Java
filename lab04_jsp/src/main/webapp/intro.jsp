<%@ page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- page 지시문(directive)의 trimDirectiveWhitespaces 속성:
        - jsp 파일이 java 코드로 변환되는 과정에서 JSP 태그들이 HTML의 빈 줄로 대체되는데,
          불필요한 빈 줄들을 삭제(true)할 것인 지, 아닌 지(false: 기본 값를 설정하는 속성)--%>

<%-- JSP 주석
1. Servlet(Server + Applet): WAS에서 실행되는 요청/응답을 처리하는 작은 자바 프로그램
    (1) 서블릿 객체의 생성과 관리, 서블릿 객체 메서드 호출은 WAS의 서블릿 컨테이너가 담당. 
    (2) 서블릿의 동작 원리:
        o. 최초 요청 -> 서블릿 객체 생성(생성자 호출) -> doGet/doPost 메서드 호출 -> 응답
        o. 요청 -> doGet/doPost 메서드 호출 -> 응답
    (3) Servlet.java 파일이 class 파일로 컴파일된 폴더 위치
        o. C:\java157\workspaces\lab-web\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps
            \lab04_jsp\WEB-INF\classes\com\itwill\jsp1
        
2. JSP(Java/Jakarta Server Page)
    (1) 서블릿은 순수 자바 코드이기 때문에 HTML 작성하기 힘듦.
    (2) HTML 형식의 파일에서 자바 코드들을 사용할 수 있도록 만든 "server-side" 문법.
    (3) JSP의 동작 원리:
        o. 최초 요청 -> jsp 파일을 java 파일로 변환 -> java 소스 코드를 class로 컴파일
            -> (서블릿) 객체를 생성 -> 메서드 호출 -> 응답
        o. 요청 -> 메서드 호출 -> 응답
    (4) JSP 파일이 Java/class 파일로 컴파일된 폴더 위치
        o. C:\java157\workspaces\lab-web\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina
            \localhost\jsp1\org\apache\jsp
        
3. JSP 구성 요소(문법, 태그)
    (1) 주석(comment)
         <%-- : jsp를 java 코드로 변환할 때 무시되는 태그.
    (2) 지시문(directive)
        <%@ ... %> : JSP 페이지 설정, 컨텐트 타입, 인코딩, 옵션들을 설정. 자바 import 생각.
    (3) 선언문(declaration)
        <%! ... %> : JSP가 자바 코드로 변환될 때, 클래스의 필드/메서드를 선언하는 태그.
    (4) 스크립트릿(scriptlet)
        <% ... %> : JSP가 자바 코드로 변환될 때, _jspService(req, resp) 메서드 안에 포함되는 자바 코드.
        메서드 안에 들어가는 내용이기 때문에, 지역 변수를 선언/초기화, 메서드 호출, 조건문, 반복문, ... 이 가능
    (5) 식, 표현식(expression)
        <%= ... %> : JSP가 자바 코드로 변환될 때, out.print() 메서드의 아규먼트로 전달되는 값.
        HTML 코드에 문자열을 삽입.
--%>

<%!
/* JSP 선언문(declaration) */
private static final String USER_NAME = "scott"; // private static 상수 필드 선언.

// 메서드 선언
private void printLog(String msg) {
	System.out.println("[intro.jsp]" + msg);
}
%>

<%
/* JSP 스크립트릿(scriptlet) */
printLog("intro.jsp 실행..."); // 이클립스 콘솔에 출력.
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP</title>
	</head>
	<body>
		<nav> <a href="/jsp1/">목차</a> </nav>
        
        <main>
            <h1>JSP 소개</h1>
            
            <%
            LocalDateTime now = LocalDateTime.now();
            String date = String.format("%d-%02d-%02d", now.getYear(), now.getMonthValue(), now.getDayOfMonth());
            String time = String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
            %>
            
            <!-- JSP 식, 표현식 (expression) -->
            <h2>사용자 이름: <%=USER_NAME %></h2> 
            <h2>날짜: <%=date %></h2> 
            <h2>시간: <%=time %></h2>
        </main>
	</body>
</html>
