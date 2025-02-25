package com.itwill.springboot2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/dept")
public class DepartmentController {
	
	private final DepartmentService deptService;

	@GetMapping("/list")
	public void list(Model model) {
		log.info("list()");
		
		List<Department> list = deptService.read();
		
		model.addAttribute("departments", list);
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable Integer id, Model model) {
		log.info("details(id={})", id);
		
		Department department = deptService.read(id);
		List<Employee> list = department.getEmployees();
		
		model.addAttribute("department", department);
		model.addAttribute("employees", list);
		
		return "/dept/details";
	}
}
