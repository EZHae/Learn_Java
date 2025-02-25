package com.itwill.springboot2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

	// 생성자와 final 필드를 사용한 의존성 주입
	private final EmployeeRepository empRepo;
	
	public List<Employee> read() {
		log.info("read()");
		
		List<Employee> employees = empRepo.findAll();
		log.info("직원 수 = {}", employees.size());
		
		return employees;
	}
	
	public Employee read(Integer id) {
		log.info("read(id={})", id);
		
		Employee employees = empRepo.findById(id).orElseThrow();
		log.info("employees={}", employees);
		
		return employees;
	}
}
