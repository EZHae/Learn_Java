package com.itwill.myblog.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.itwill.myblog.service.MemberService;

/**
 * Servlet implementation class MemberEmailCheckController
 */
@WebServlet(name = "memberEmailCheckController", urlPatterns = { "/member/emailcheck" })
public class MemberEmailCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService = MemberService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEmailCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		int result = memberService.emailCheck(email);
		
	    response.setContentType("text/plain");
	    response.getWriter().print(result);
	}

}
