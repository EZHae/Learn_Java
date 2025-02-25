package com.itwill.jsp1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class SessionController
 */
@WebServlet(name = "sessionController", urlPatterns = { "/session" })
public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionController() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SessionController::doGet() 호출");
		
		// WAS에서 (힙에) 저장하는, 클라이언트마다 매핑된 세션 객체를 찾음.
		HttpSession session = request.getSession();
		/* request.getSession() 동작방식:
		 * 	(1) 클라이언트에서 JSESSIONID 쿠키를 보낸 경우에는, 
		 *  	쿠키의 값(세션 아이디)을 이용해서 힙에 생성된 세션 객체를 찾음.
		 *  (2) 클라이언트에서 JSESSIONID 쿠키를 보내지 않은 경우이거나, 
		 *  	세션 아이디의 세션이 이미 만료돼서 힙에서 삭제된 경우에는
		 *  	새로운 세션 객체를 생성(새로운 세션 아이디가 부여)하고, 리턴.
		*/
		
		// 세션 객체에 정보(데이터)를 저장.
		session.setAttribute("nickname", "관리자");
		
		// 세션 만료 시간을 10초로 설정.
		session.setMaxInactiveInterval(10);
		
		// 뷰로 이동
		request.getRequestDispatcher("/WEB-INF/views/session.jsp").forward(request, response);
	}

}
