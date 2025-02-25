<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MVC</title>
	</head>
	<body>
        <%@ include file="../../header.jspf" %>
        <%-- 
        ../ : 현재 폴더를 기준으로 상위폴더로 올라가기 (상대경로)
        예) 현재 폴더 위치: webapp/WEB-INF/views/contact.jsp
            webapp 폴더에 있는 header.jspf에 접근하려면 ../를 두번 사용해야 함
        --%>
		<h1>MVC</h1>
        
        <h2>연락처 입력 페이지</h2>
        <%-- <form>태그의 action 속성을 설정하지 않으면 현재 요청 주소 그대로 요청을 보냄. --%>
        <form action="" method="post">
            <div>
                <input type="number" name="id" placeholder="번호" autofocus>
            </div>
            <div>
                <input type="text" name="name" placeholder="이름">
            </div>
            <div>
                <input type="text" name="phone" placeholder="전화번호">
            </div>
            <div>
                <input type="email" name="email" placeholder="이메일">
            </div>
            <div>
                <input type="submit" value="저장"> <%-- <input>에 입력되어 있는 내용을 서버로 요청 보냄 --%>
                <input type="reset" value="취소"> <%-- <input>에 입력되어 있는 내용 모두 지움 --%>
            </div>
        </form>
	</body>
</html>