package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final 필드를 초기화할 수 있는 아규먼트를 갖는 생성자
@Controller
@RequestMapping("/post")
//-> 이 클래스(PostConroller)의 모든 메서드의 매핑 주소는 "/post"로 시작.
//	 GET/POST 등 모든 요청 방식(method)를 처리.
public class PostController {
	
	// final 필드와 생성자를 사용한 의존성 주입:
	private final PostService postService;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.debug("list()");
		// 컨트롤러 메서드의 리턴 타입이 void인 경우 요청 주소
		// -> /WEB-INF/views/post/list.jsp
		
		List<Post> list = postService.read();
		model.addAttribute("posts", list);
	}
	
	@GetMapping("/create")
	public void create() {
		log.debug("create()");
	}
	
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
		log.debug("create(dto={})", dto);
		
		// controller ==> service 메서드를 호출 & DTO를 전달.
		int result = postService.create(dto);
		log.debug("result={}", result);
		
		return "redirect:/post/list";
	}
	
	@GetMapping({ "/details", "/modify" })
	//-> GET방식의 /post/details와 /post/modify 요청 주소들을 처리하는 컨트롤러
	//-> 메서드의 리턴 타입이 void이므로
	//		요청주소가 details일 때 뷰의 이름은 details.jsp
	//		요청주소가 modify일 때 뷰의 이름은 modify.jsp
	public void details(@RequestParam Integer id, Model model) {
		log.debug("details(id={})", id);
		
		// 서비스 계층의 메서드를 호출해서 상세보기 화면에 필요한 데이터를 읽어옴.
		Post post = postService.read(id);
		
		// 상세 보기 내용을 view에 전달하기 위해 Model에 속성으로 추가.
		model.addAttribute("post", post);
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		log.debug("delete(id={})", id);
		
		int result = postService.delete(id);
		log.debug("delete result={}", result);
		
		return "redirect:/post/list";
	}
	
	@PostMapping("/update")
	public String update(PostUpdateDto dto) {
		log.debug("update(dto={})", dto);
		
		postService.update(dto);
		
		String url = "/post/details?id=" + dto.getId();
		return "redirect:" + url;
	}
	
	@GetMapping("/search")
	public String search(PostSearchDto dto, Model model) {
		log.debug("search(dto={})", dto);
		
		// 서비스 계층의 메서드를 호출해서 검색 결과 리스트를 가져옴.
		List<Post> list = postService.read(dto);
		
		model.addAttribute("posts", list);
		
		return "post/list"; //-> /WEB-INF/views/post/list.jsp
		
	}
}
