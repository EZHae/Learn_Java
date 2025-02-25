package com.itwill.springboot4.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot4.domain.Post;
import com.itwill.springboot4.dto.PostCreateDto;
import com.itwill.springboot4.dto.PostPageDto;
import com.itwill.springboot4.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		log.info("list()");
		
		int pageSize = 10; // 한 페이지에 보여줄 항목 개수
		int pageBlock = 5; // 한 번에 보여줄 페이지 버튼 개수
		
		PostPageDto postPageDto = postService.read(page, pageSize, pageBlock);
		
		model.addAttribute("posts", postPageDto.getPost());
		model.addAttribute("currentPage", postPageDto.getCurrentPage());
		model.addAttribute("totalPages", postPageDto.getTotalPages());
		model.addAttribute("startPage", postPageDto.getStartPage());
		model.addAttribute("endPage", postPageDto.getEndPage());
        
		return "/post/list";
    }
    
    @GetMapping("/create")
    public void create() {
    	log.info("create()");
    }
    
    @PostMapping("/create")
    public String createPost(PostCreateDto dto) {
    	log.info("createPost()");
    	
    	long id = postService.create(dto);
    	
    	String url = "/post/details/" + id;
    	
    	return "redirect:" + url;
    }
    
    @GetMapping({"/details/{id}", "/update/{id}"})
    public String details(@PathVariable long id, Model model, HttpServletRequest request) {
    	log.info("details()");
    	String requestURI = request.getRequestURI();
    	log.info(requestURI);
    	
    	Post post = postService.read(id);
    	
    	model.addAttribute("post", post);
    	
    	return requestURI.contains("/details") ? "/post/details" : "/post/update";
    }
    
}
