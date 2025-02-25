package com.itwill.springboot2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot2.domain.Employee;

/*
 * Repository<T, ID>
 * |__ CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>
 * 	  |__ JpaRepository<T, ID>
 * 
 * T: 엔터티 클래스
 * ID: 엔터티 클래스의 @ID 필드의 데이터 타입
 */

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByEnameIgnoreCase(String ename);
	
	List<Employee> findByEnameContainingIgnoreCase(String ename);
}
