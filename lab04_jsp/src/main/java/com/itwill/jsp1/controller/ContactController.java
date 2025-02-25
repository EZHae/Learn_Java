package com.itwill.jsp1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.itwill.jsp1.model.Contact;

/**
 * Servlet implementation class ContactController
 */
@WebServlet(name = "contactController", urlPatterns = { "/mvc" })
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactController() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ContactController::doGet() 호출");
		
		// 요청을 /WEB-INF/views/contact.jsp 파일로 전달(forward)
		/* WAS의 webapp/WEB-INF 폴더는 클라이언트(외부)는 접근할 수 없는 폴더
		 * 하지만, 서버(내부)에서는 접근할 수 있는 폴더.
		 * => 서블릿에서 webapp/WEB-INF 폴더 아래의 jsp파일로 forward가 가능. */
		request.getRequestDispatcher("/WEB-INF/views/contact.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ContactController:doPost() 호출");
		
		// 클라이언트가 전송한 폼 양식 데이터(요청 파라미터)를 읽음.
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		Contact contact = new Contact(id, name, phone, email);
		
		System.out.println(contact);
		
		// "redirect" 방식의 페이지 이동.
		response.sendRedirect("/jsp1/");
	}
}