package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.dto.DepartmentDetailsDto;
import com.itwill.springboot3.repository.DepartmentRepository;
import com.itwill.springboot3.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartmentService {

	private final DepartmentRepository deptRepo;
	private final EmployeeRepository empRepo;
	
	public Page<Department> read(Pageable pageable) {
		Page<Department> list = deptRepo.findAll(pageable);
		return list;
	}
	
	public DepartmentDetailsDto read(Integer id) {
		Department dept = deptRepo.findById(id).orElseThrow();
		List<Employee> emps = empRepo.findByDepartmentId(id);
		
		DepartmentDetailsDto deptDto = DepartmentDetailsDto.fromEntity(dept, emps);
		return deptDto;
	}
}
