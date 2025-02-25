package com.itwill.myblog.filter;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter extends HttpFilter {
       
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

	/**
     * @see HttpFilter#HttpFilter()
     */
    public AuthenticationFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request; // casting
		HttpServletResponse resp = (HttpServletResponse) response; // casting
		
		// URL(Uniform Resource Location): 포로토콜, 서버, 포트번호, contextPath를 포함 (예 - http://localhost:8080/jsp2/post/details)
		String reqUrl = req.getRequestURL().toString();
		log.debug("request URL = {}", reqUrl);

		// URI(Uniform Resource Indentifier): 포로토콜, 서버, 포트번호를 미포함 (예 -
		// /jsp2/post/details)
		String reqUri = req.getRequestURI();
		log.debug("request URI = {}", reqUri);

		// contextpath: 요청 주소의 contextpath를 출력 (예 - /jsp2)
		String contextPath = req.getContextPath();
		log.debug("context path = {}", contextPath);

		// query string: 요청 주소의 query string을 출력 (예 - id=26)
		String qs = req.getQueryString();
		log.debug("query string = {}", qs);

		// 로그인 이후에 이동할 페이지(타겟)
		String target = null;
		if (qs != null) { // query string이 있는 경우
			target = URLEncoder.encode(reqUrl + "?" + qs, "UTF-8");
		} else { // query string이 없는 경우
			target = URLEncoder.encode(reqUrl, "UTF-8");
		}
		log.debug("target = {}", target);

		// 세션에 signedInMember 속성이 있는지 체크:
		HttpSession session = req.getSession();
		Object signedInMember = session.getAttribute("signedInMember");
		if (signedInMember != null) {
			log.debug("로그인 사용자: {}", signedInMember);
			// 필터 체인을 통해서(그 다음 필터 또는 서블릿으로) 요청을 전달.
			chain.doFilter(request, response);
		} else {
			log.debug("로그아웃 상태 --> 로그인 페이지로 이동 --> 로그인 성공 후 target으로 이동");

			String url = req.getContextPath() + "/member/signin?target=" + target;
			resp.sendRedirect(url);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
