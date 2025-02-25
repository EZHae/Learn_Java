package com.itwill.spring2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	
	private final ServletContext servletContext;
	
	@GetMapping("/")
	public String home(ServletContext servletContext) {
		log.debug("home()");
		
		String projectPath = servletContext.getRealPath("/");
		log.debug(projectPath);
		
		
		return "home"; // 뷰(jsp 파일)의 이름
		
	}
}
