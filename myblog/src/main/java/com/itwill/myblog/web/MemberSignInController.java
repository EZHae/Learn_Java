package com.itwill.myblog.web;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.myblog.domain.Member;
import com.itwill.myblog.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberSignInController
 */
@WebServlet(name = "memberSignInController", urlPatterns = { "/member/signin" })
public class MemberSignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberSignInController.class);
	private final MemberService memberService = MemberService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSignInController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/signin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String target = request.getParameter("target");
		log.debug("doPost(username={}, password={}", username, password);
		log.debug("target={}", target);
		
		
		Member member = memberService.signIn(username, password);
		if (member != null) { // username과 passwrod가 일치하는 사용자가 있는 경우 -> 로그인 성공
			// 세션에 로그인 정보를 저장 -> target 페이지로 redirect
			HttpSession session = request.getSession();
			session.setAttribute("signedInMember", member.getUserName());
			
			if (target != null && !target.equals("")) {
				response.sendRedirect(target);
			} else {
				String url = request.getContextPath() + "/"; // 홈 페이지
				response.sendRedirect(url);
			}
		} else { // username과 passwrod가 일치하는 사용자가 없는 경우 -> 로그인 실패
			// 다시 로그인 페이지로 이동(redirect)
			String url = request.getContextPath() + "/member/signin?result=f&target=" + URLEncoder.encode(target, "UTF-8");
			log.debug("로그인 실패: redirect to {}", url);
			response.sendRedirect(url);

		}
	}

}
