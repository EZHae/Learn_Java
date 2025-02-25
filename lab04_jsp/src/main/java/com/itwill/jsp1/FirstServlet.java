package com.itwill.jsp1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/* WAS(Web Application Server): 
 * 		웹 요청(request)과 응답(reponse)를 처리하는 프로그램
 * 			- 예) Tomcat
 * 		WAS는 시작될 때 web.xml 파일을 읽어서 웹 서비스 준비(초기화)를 진행한다.
 * 			- 초기 페이지는 무엇인지, 어떤 servlet을 사용 중인지 등
 * 			* web.xml 파일: 배포 설명자(관리자)(deployment descriptor)
 * 클라이언트(브라우저)에서 요청이 왔을 때 WAS는 web.xml에 작성된 서블릿 설정을 보고,
 * 요청 주소에 매핑(mapping)된 서블릿 클래스의 doGet() 또는 doPost() 메서드를 호출함.
 * 		Servlet(Server + Applet): 서버에서 실행되는 작은 자바 프로그램.
 * 
 * 서블릿 컨테이너(Servlet container):
 * 		- 서블릿 객체를 생성/관리, 필요할 때 서블릿 객체의 메서드를 호출하는 프로그램. 
 * 
 * 서블릿 설정: 서블릿 클래스와 요청 주소를 매핑 설정.
 * 		1. web.xml 파일에서 <servlet>, <servlet-mapping>으로 설정.
 * 		2. 각각의 서블릿 클래스에서 @WebServlet(name = "servletname", urlPattern = ({ "pattenname" }) 애너테이션으로 설정.
 * 		(주의) 하나의 서블릿 클래스는 web.xml 또는 애너테이션 중 한 가지 방법으로만 설정해야 함.
 * 
 * 서블릿 동작 원리:
 * 		- 클라이언트의 요청 -> 서블릿 객체 생성(이미 생성되어 있으면 생략) -> doGet() / doPost() 호출
 */

/**
 * Servlet implementation class FirstServlet
 */
// @WebServlet(name = "firstServlet", urlPatterns = { "/ex1" })
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
    	System.out.println("FirstServlet() 생성자 호출");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // GET 방식의 요청일 때 WAS가 호출하는 메서드.
    /* 파라미터 request: 클라이언트에서 서버로 보낸 요청의 정보 등을 저장하고 있는 객체.
     * 파라미터 response: 서버가 클라이언트로 보낼 응답의 데이터, 기능 등을 갖는 객체.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request: 클라이언트(브라우저)에서 WAS로 데이터를 받아올 때 (예시: <form>의 하위 태그들의 value) / 클라이언트 => WAS
//		response: WAS에서 클라이언트(브라우저)로 데이터를 내보낼 때 (예시: HTML 생성) / WAS => 클라이언트
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("FirstServlet::doGet() 호출");
		
		// WAS가 클라이언트로 보내는 컨텐트 타입을 설정.
		response.setContentType("text/html; charset=UTF-8");
		
		// 응답으로 보낼 HTML을 작성.
		PrintWriter writer = response.getWriter();
		writer.append("<!doctype html")
				.append("<html>")
				.append("	<head>")
				.append("		<meta charset='UTF-8'/>") // 작은따옴표가 아닌 큰 따옴표를 사용하고싶으면 \" 사용
				.append("		<title>Servlet</title>")
				.append("	</head>")
				.append("	<body>")
				.append("		<h1>첫번째 서블릿</h1>")
				.append("		<a href='/jsp1/'>목차</a>")
				.append("	</body>")
				.append("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// POST 방식의 요청일 때 WAS가 호출하는 메서드.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
