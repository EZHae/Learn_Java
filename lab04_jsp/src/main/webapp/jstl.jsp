<%@page import="com.itwill.jsp1.model.Contact"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL</title>
	</head>
	<body>
		<%@ include file="header.jspf" %>
        
        <main>
            <h1>JSTL(Jakarta Standard Tag Library)</h1>
            <%-- JSTL 사용하기
                1. pom.xml 파일에 의존성(dependency)을 추가.
                    o. jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:version
                             그룹 아이디       |      아키팩트 아이디       | 버전
                    o. org.glassfish.web:jakarta.servlet.jsp.jstl:version
                         그룹 아이디    |     아키팩트 아이디    | 버전
                2. JSTL을 사용하는 jsp 파일에서 지시문 작성. <%@ taglib prefix="" uri="" %> 
            --%>
            
            <% // 더미 데이터
            String [] sns = { "인*", "얼굴책", "X", "싸이월드" };
            
            // EL에서 리스트를 사용할 수 있도록 contacts를 내장 객체에 저장(pageContext)
            pageContext.setAttribute("sites", sns);
            %>
            
            <h2>스크립트릿(scriptlet), 표현식(expression) 사용한 리스트</h2>
            <ul>
                <% for (String s : sns) { %>
                    <li><%= s %></li>
                <% } %>
            </ul>
            
            <h2>JSTL, EL(Expression Language) 사용한 리스트</h2>
            <ul>
                <c:forEach var="s" items="${ sites }">
                    <li>${ s }</li>
                </c:forEach>
            </ul>
            
            <% // 더미 데이터
            ArrayList<Contact> contacts = new ArrayList<>();
            for (int i = 0; i < 5; i ++) {
                Contact c = new Contact(i, "이름_"+i, "전화번호_"+i, "이메일_"+i);
                contacts.add(c);
            }
            
            // EL에서 리스트를 사용할 수 있도록 contacts를 내장 객체에 저장(pageContext)
            pageContext.setAttribute("contactList", contacts);
            %>
            <h2>더미 데이터를 활용, scriptlet, expression 사용한 데이터 테이블 작성</h2>
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>이름</th>
                        <th>전화번호</th>
                        <th>이메일</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Contact c : contacts) { %>
                        <tr>
                            <td><%= c.getId() %></td>
                            <td><%= c.getName() %></td>
                            <td><%= c.getPhone() %></td>
                            <td><%= c.getEmail() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
            
            <h2>더미 데이터를 활용, JSTL, EL 사용한 데이터 테이블 작성</h2>
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>이름</th>
                        <th>전화번호</th>
                        <th>이메일</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="c" items="${ contactList }">
                        <tr>
                            <%-- EL은 프로퍼티 이름으로 getter 메서드를 찾음.
                                 $ {c. id} = <%= c.getId() %> } --%>
                            <td>${ c.id }</td>
                            <td>${ c.name }</td>
                            <td>${ c.phone }</td>
                            <td>${ c.email }</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <h2>JSTL URL 태그</h2>
            <div><a href="click_result.jsp?username=gu&est&color=crimson">클릭 1</a></div>
            <%-- URL에서 &는 요청 파라미터를 구분하기 위한 구분자로 사용됨.
                 '&'를 포함하는 문자열을 요청 파라미터 값으로 전달하기 위해서는
                 '&'에 해당하는 UTF-8 코드값을 URL에 인코딩해야 함 --%>
            <div>
                <c:url value="click_result.jsp" var="resultPage">
                    <c:param name="username" value="gue?st"></c:param>
                    <%-- JSTL의 URL 태그를 이용하면 인코딩 없이 처리 가능 --%>
                    <c:param name="color" value="dodgerblue"></c:param>
                </c:url>
                <a href="${ resultPage }">클릭 2</a>
            </div>
            
        </main>
	</body>
</html>
