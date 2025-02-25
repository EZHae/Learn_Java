package com.itwill.springboot3.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/emp")
public class EmployeeController {

	private final EmployeeService empService;
	
	@GetMapping("/list")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		log.info("list()");
		
		int pageSize = 10; // 한 페이지에 보여줄 항목 개수
		int pageBlock = 5; // 한 번에 보여줄 페이지 버튼 개수
		int basePage = page - 1; // 주소가 0으로 시작하는 것을 1로 바꾸고 실제로 연산은 0으로 되게 하기 위해
		
		// 페이징 객체 생성 (연산은 0부터)
		Pageable pageable = PageRequest.of(basePage, pageSize, Sort.by("id").ascending());
		
		// 설정한 페이징 객체에 맞는 Employee 조회
		Page<Employee> empPage = empService.read(pageable);
		
		int totalPages = empPage.getTotalPages(); // 전체 페이지 개수
		int currentPage = empPage.getNumber() + 1; // 현재 페이지 번호 (사용자에게 보이는 부분이니 다시 +1)
		
		// 페이지 그룹 계산
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		int endPage = Math.min(startPage + pageBlock - 1, totalPages);
		
		model.addAttribute("emps", empPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "emp/list";
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable Integer id, Model model) {
		log.info("details");
		
		Employee emp = empService.read(id);
		model.addAttribute("emp", emp);
		
		return "/emp/details";
	}
}
