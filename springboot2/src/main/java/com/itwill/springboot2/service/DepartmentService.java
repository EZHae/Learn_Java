package com.itwill.springboot2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartmentService {

	private final DepartmentRepository deptRepo;
	
	public List<Department> read() {
		log.info("read()");
		
		List<Department> departments = deptRepo.findAll();
		log.info("부서 개수 = {}", departments.size());
		
		return departments;
	}
	
	public Department read(Integer id) {
		log.info("details()");
		
		Department department = deptRepo.findById(id).orElseThrow();
		log.info("부서 직원 수 = {}", department.getEmployees().size());
		
		return department;
	}
}
