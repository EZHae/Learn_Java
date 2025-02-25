package com.itwill.spring2.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
// @WebFilter(filterName = "authenticationFilter", urlPatterns = { "/authenticationFilter" })
@Slf4j
public class AuthenticationFilter extends HttpFilter {
   
	private static final long serialVersionUID = 1L;

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
		// 필터 객체가 소멸될 때(WAS가 종료될 때)
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 로그인한 사용자(권한이 있는 사용자)는 필터 체인을 진행.
		// 로그인하지 않은 사용자(권한이 없는 사용자)는 로그인 페이지로 이동(redirect)
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		// 세션을 가져옴.
		HttpSession session = req.getSession();
		
		// 세션에서 로그인 사용자 속성(signedInUser attribute) 값을 읽음.
		Object signedInUser = session.getAttribute("signedInUser");
		
		if (signedInUser != null && !signedInUser.equals("")) { // 로그인이 되어 있는 상태.
			log.debug("로그인 상태: username={}", signedInUser);
			
			// 필터 체인의 다음 단계(다음 필터 또는 서블릿)으로 request를 전달.
			chain.doFilter(request, response);
			return;
		} else {
			log.debug("로그아웃 상태 --> 로그인 페이지로 이동");
			
			// 로그인 이후에 최초로 요청 주소로 이동(redirect) 하기 위해서
			String url = req.getRequestURL().toString();
			String qs = req.getQueryString();
			
			String target = null;
			if (qs == null) {
				target = URLEncoder.encode(url, "UTF-8");
			} else {
				target = URLEncoder.encode(url + "?" + qs, "UTF-8");
			}
			log.debug("target={}", target);
			
			String signInPage = req.getContextPath() + "/user/signin?target=" + target;
			
			resp.sendRedirect(signInPage);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// WAS가 필터를 생성한 이후에 필터에서 촉화 작업이 필요한 코드들을 작성.
	}

}
