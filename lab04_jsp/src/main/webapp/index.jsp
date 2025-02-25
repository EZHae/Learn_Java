<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>JSP1</title>
    </head>
    <body>
        <header>
            <h1>Servlet/JSP 소개</h1>
            <div><%= LocalDateTime.now() %></div>
            <div>안녕하세요, ${ nickname }!</div>
        </header>
        
        <nav>
            <h2>목차</h2>
            <ul>
                <li> <a href="ex1">첫번째 서블릿</a> </li>
                <li> <a href="ex2">두번째 서블릿</a> </li>
                <li> <a href="ex3">포워드(Forward)</a> </li>
                <li> <a href="ex4">리다이렉트(Redirect)</a> </li>
                <li> <a href="intro.jsp">JSP 소개</a> </li>
                <li> <a href="main.jsp">include 지시문</a> </li>
                <li> <a href="scriptlet.jsp">스크립트릿(scriptlet)</a> </li>
                <li> <a href="form.jsp">폼(form) 양식</a> </li>
                <li> <a href="actiontag.jsp">JSP Action Tag</a> </li>
                <li> <a href="el.jsp">EL(Expression Language)</a> </li>
                <li> <a href="jstl.jsp">JSTL</a> </li>
                <li> <a href="mvc">MVC</a> </li>
                <li> <a href="cookie">Cookie</a> </li>
                <li> <a href="session">Session</a> </li>
            </ul>
        </nav>
    </body>
</html>