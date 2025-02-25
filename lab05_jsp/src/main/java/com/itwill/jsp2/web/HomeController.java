package com.itwill.jsp2.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(name = "homeController", urlPatterns = { "" })
// urlPatterns이 빈 문자열("")인 경우, 컨텍스트 루트(context root)로 들어오는 요청을 처리하게 됨.
// (컨텍스트 루트) http://localhost:8080/jsp2
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("Level: debug_doGet()");
		
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}
}
