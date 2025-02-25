package com.itwill.springboot3.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

	private final EmployeeRepository empRepo;
	
	public Page<Employee> read2(int pageNo, Sort sort) {
		
		Pageable pageable = PageRequest.of(pageNo, 10, sort);
		
		Page<Employee> page = empRepo.findAll(pageable);
		log.info("hasPrevious={}", page.hasPrevious());
		log.info("hashNext={}", page.hasNext());
		log.info("getNumber={}", page.getNumber());
		
		return page;
	}
	
	public Page<Employee> read(Pageable pageable) {
		Page<Employee> list = empRepo.findAll(pageable);
		return list;
	}
	
	public Employee read(Integer id) {
		Employee emp = empRepo.findById(id).orElseThrow();
		return emp;
	}
	
	
}
