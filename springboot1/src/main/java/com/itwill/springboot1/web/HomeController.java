package com.itwill.springboot1.web;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itwill.springboot1.domain.Author;
import com.itwill.springboot1.domain.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		log.info("home() 호출");
		
		LocalDateTime now = LocalDateTime.now();
		
		Author author = Author.builder()
				.firstName("강").lastName("한").build();
		Book book = Book.builder()
				.id(100100).title("채식주의자").author(author).build();
		
		model.addAttribute("currentTime", now);
		model.addAttribute("book", book);
		
		return "index";
		// 컨트롤러 메서드의 리턴 값 (문자열) -> 뷰의 이름
		// 스프링 부트 프로젝트에서는 src/main/resources/templates/리턴값.html
	}
	
	@GetMapping("/book/list")
	public void bookList(Model model) {
		log.info("bookList()");
		
		ArrayList<Book> list = new ArrayList<>();
		
		for (Integer i = 1; i <= 5; i++) {
			Author author = Author.builder().firstName(i.toString()).lastName("저자").build();
			Book book = Book.builder().id(i).title("제목" + i).author(author).build();
			list.add(book);
		}
		list.add(Book.builder().id(000).title("제목000").build());
		model.addAttribute("books", list);
	}
	
	@GetMapping("/book/details")
	public void details (Integer id, Model model) {
		log.info("details(id={})", id);
		
		Book book = Book.builder().id(id).title("무제" + id).build();
		model.addAttribute("book", book);
	}
	
	@GetMapping("/book/details/{id}")
	public String detailsPath (@PathVariable Integer id, Model model) {
		log.info("detailsPath(id={})", id);
		
		Book book = Book.builder().id(id).title("무제" + id).build();
		model.addAttribute("book", book);
		
		return "book/details";
	}
}
