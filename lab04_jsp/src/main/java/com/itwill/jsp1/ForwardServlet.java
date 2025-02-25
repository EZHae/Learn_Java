package com.itwill.jsp1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class FowardServlet
 */
@WebServlet(name = "fowardServlet", urlPatterns = { "/ex3" })
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForwardServlet() {
        System.out.println("ForwardServlet 생성자 호출");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ForwardServlet::doGet() 호출");
		
		/* 요청이 오면 WAS는 web.xml또는 @WebServlet 애너테이션에서 설정된
		 * URL 매핑에 따라서 요청을 처리할 수 있는 서블릿 객체의 메서드(doGet,doPost)를 호출.
		 * 서블릿은 응답으로 보낼 HTML을 작성.
		 * 서블릿에서 HTML을 작성하는 것은 너무 번거로움.
		 * 서블릿은 JSP로 요청을 전달하고 JSP가 HTML을 작성하는 것이 더 쉬움.
		 * WAS는 JSP가 작성한 HTML을 클라이언트에게 응답보냄.
		 */
		
		/* "forward" 방식의 웹페이지 이동:
		 * 		- 같은 WAS의 같은 웹 애블리케이션 안에서만 페이지를 이동하는 방식. (다른 프로젝트간 이동 불가)
		 * 		- 최초 요청 주소가 바뀌지 않음.
		 * 		- request, response 객체가 유지됨. (WAS가 받은 객체를 JSP에도 그대로 전달됨)
		 */
		
		// example.jsp에 서블릿 객체가 받은 request와 response를 다시 전달한다.
		request.getRequestDispatcher("example.jsp")
			.forward(request, response);
	}
}
