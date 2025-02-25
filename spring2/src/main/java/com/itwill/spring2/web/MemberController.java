package com.itwill.spring2.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.spring2.domain.Member;
import com.itwill.spring2.dto.MemberSignInDto;
import com.itwill.spring2.dto.MemberSignUpDto;
import com.itwill.spring2.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("signup")
	public void signUp() {
		log.debug("[GET] signUp()");
	}
	
	// username 중복체크
	// 중복되지 않은 username이면 "Y", 중복된 username이면 "N"을 전송(응답)
	@ResponseBody
	@GetMapping("/checkusername")
	public ResponseEntity<String> checkUsername(@RequestParam String username) {
		log.debug("checkUsername(username={})", username);
		
		boolean result = memberService.checkUsername(username);
		
		if (result) {
			return ResponseEntity.ok("Y");
		} else {
			return ResponseEntity.ok("N");
		}
	}
	
	// email 중복체크
	// 중복되지 않은 email이면 "Y", 중복된 email이면 "N"을 전송(응답)
	@ResponseBody
	@GetMapping("/checkemail")
	public ResponseEntity<String> checkEmail(@RequestParam String email) {
		log.debug("checkEmail(email={})", email);
		
		boolean result = memberService.checkEmail(email);
		
		return result ? ResponseEntity.ok("Y") : ResponseEntity.ok("N");
	}
	
	
	@PostMapping("signup")
	public String signUp(MemberSignUpDto dto) {
		log.debug("[POST] signUp(dto={})", dto);
		
		memberService.create(dto);
		
		// 회원 가입 성공후에는 로그인 페이지로 이동
		return "redirect:/user/signin";
	}
	
	@GetMapping("/signin")
	public void signIn() {
		log.debug("[GET] signIn()");
	}
	
	@PostMapping("signin")
	public String signIn(MemberSignInDto dto, 
			@RequestParam(name = "target", defaultValue = "") String target,
			HttpSession session) throws UnsupportedEncodingException {
		log.debug("[POST] signIn(dto={}, target={}", dto, target);
		
		Member member = memberService.signIn(dto);
		String targetPage = null;
		if (member != null) { // member가 null이 아닌 경우 (일치하는 아이디와 비밀번호가 있음)
			// "signedInUser" 라는 이름으로 dto.username을 세션에 등록
			session.setAttribute("signedInUser", member.getUsername());
			
			// 로그인 성공 이후 이동할 페이지 설정.
			targetPage = target.equals("") ? "/" : target;
			
		} else { // member가 null인 경우 (일치하는 아이디와 비밀번호가 없음)
			// 다시 로그인 페이지로 리다이렉트 (다시 로그인 시도)
			targetPage = "/user/signin?result=f&target=" + URLEncoder.encode(target, "UTF-8");
		}
		return "redirect:" + targetPage;
	}
	
	@GetMapping("signout")
	public String signOut(HttpSession session) {
		session.invalidate();
		
		return "redirect:/user/signin";
	}
}
