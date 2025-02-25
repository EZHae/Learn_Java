package com.itwill.jsp2.web.user;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Member;
import com.itwill.jsp2.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserSignInController
 */
@WebServlet(name = "userSignInController", urlPatterns = { "/user/signin" })
public class UserSignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserSignInController.class);
	private final MemberService memberService = MemberService.INSTANCE;
	
	private String referer = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignInController() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doGet()");
		
		request.getRequestDispatcher("/WEB-INF/views/user/signin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 양식 데이터(username, password)를 읽음.
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String target = request.getParameter("target");
		log.debug("doPost(username={}, password={}", username, password);
		log.debug("target={}", target);
		
		// 서비스 계층의 메서드를 호출해서 로그인 성공/실패 여부 판단
		Member member = memberService.signIn(username, password);
		if (member != null) { // username과 passwrod가 일치하는 사용자가 있는 경우 -> 로그인 성공
			// 세션에 로그인 정보를 저장 -> target 페이지로 redirect
			HttpSession session = request.getSession();
			session.setAttribute("signedInUser", member.getUserName());
			
			if (target != null && !target.equals("")) {
				response.sendRedirect(target);
			} else {
				String url = request.getContextPath() + "/"; // 홈 페이지
				response.sendRedirect(url);
//				response.sendRedirect(referer);
			}
			
//			String url = request.getContextPath() + "/post/list";
//			log.debug("로그인 성공: redirect to {}", url);
//			response.sendRedirect(url);
			
//			log.debug("로그인 성공: redirect to {}", referer);
			
		} else { // username과 passwrod가 일치하는 사용자가 없는 경우 -> 로그인 실패
			// 다시 로그인 페이지로 이동(redirect)
			String url = request.getContextPath() + "/user/signin?result=f&target=" + URLEncoder.encode(target, "UTF-8");
			log.debug("로그인 실패: redirect to {}", url);
			response.sendRedirect(url);
			
			
//			String encoderToTarget = URLEncoder.encode(target, "UTF-8");
//			String urlTest = request.getContextPath() + "/user/signin?target=" + encoderToTarget;
//			response.sendRedirect(urlTest);
		}
		
	}

}
