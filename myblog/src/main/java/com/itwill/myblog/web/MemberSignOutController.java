package com.itwill.myblog.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberSignOutController
 */
@WebServlet(name = "memberSignOutController", urlPatterns = { "/member/signout" })
public class MemberSignOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberSignOutController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSignOutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doGet()");
		
		/* 로그아웃:
			(1) 세션에 저장된 로그인 관련 정보들(signedInUser)을 삭제.
			(2) 세션 객체를 무효화(invalidate) - 기존에 생성되어있는 세션 객체를 지움.
			(2)번만 실행하면 (1)은 자동으로 실행됨. */
		
		/*(1)*/
		HttpSession session = request.getSession();
//		session.removeAttribute("signedInUser");
		
		/*(2)*/
		session.invalidate();
		
		// 로그아웃 이후에 홈페이지로 이동.
		String url = request.getContextPath() + "/";
		log.debug("로그아웃: redirect to {}", url);
		response.sendRedirect(url);
	}
}
