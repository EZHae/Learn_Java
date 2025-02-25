package com.itwill.springboot3.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.springboot3.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	/* JPA Query Method
	 * JPA에서 미리 약속된 키워드들과 필드 이름들을 사용해서 SQL 실행문을 생성
	 * 메서드 이름을 카멜 표기법으로 작성 */
	
	// 부서번호(department_id)가 일치하는 직원(들) 검색
	// select * from employees where department_id = ?
	List<Employee> findByDepartmentId(Integer id);
	
	// 이름(first_name)이 일치하는 직원(들) 검색
	// select * from employees where first_name = ?
	List<Employee> findByFirstName(String firstName);
	
	// 이름(first_name)이 일치하는 직원(들), 대/소문자 구분 없이 검색
	// select * from employees where upper(first_name) = upper(?)
	List<Employee> findByFirstNameIgnoreCase(String firstName);
	
	// 이름(first_name)이 포함된 문자열로 검색
	// select * from employees where first_name like '%?%'
	List<Employee> findByFirstNameContaining(String keyword);
	
	// Containing과 like 키워드 비교
	// Containing을 사용하면 아규먼트의 앞/뒤에 '%'를 붙이고 like 검색을 실행
	// Like를 사용할 때는 아규먼트의 원하는 곳에 '%' 붙일 수 있음.
	// findByFirstNameLike(keyword)
	//	select * from employees where first_name like '?'
	//	ex) findByFirstNameLike("a%ffd")
	//		= select * from employees where first_name like 'a%ffd'
	List<Employee> findByFirstNameLike(String keyword);
	
	// Homework
	// 이름(first_name)에 포함된 단어로 검색, 단어 대/소문자 구분 없이 검색
	// select * from employees where upper(first_name) like '%' || upper(?) || '%'
	List<Employee> findByFirstNameContainingIgnoreCase(String keyword);

	// 이름(first_name)에 포함된 단어로 검색, 단어 대/소문자 구분 없이 검색, 정렬 순서는 이름 내림차순.
	// select * from employees where upper(first_name) like '%' || upper(?) || '%' order by first_name desc
	List<Employee> findByFirstNameContainingIgnoreCaseOrderByFirstNameDesc(String keyword);

	// 대소문자 구분없이 성(last_name) 또는 이름(first_name)에 문자열이 포함된 직원 검색
	// select * from employees where upper(first_name) like '%' || upper(?) || '%' or upper(last_name) like '%' || upper(?) || '%'
	List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

	// 급여가 어떤 값을 초과하는 직원들 검색
	// select * from employees where salary > ?
	List<Employee> findBySalaryGreaterThan(Integer lessValue);

	// 급여가 어떤 값 미만인 직원들 검색
	// select * from employees where salary < ?
	List<Employee> findBySalaryLessThan(Integer greaterValue);

	// 급여가 어떤 범위 안에 있는 직원들 검색
	// select * from employees where salary between ? and ?
	List<Employee> findBySalaryBetween(Integer lessValue, Integer greaterValue);

	// 입사날짜가 특정 날짜 이전인 직원들 검색
	// select * from employees where hire_date < ?
	List<Employee> findByHireDateBefore(LocalDate lessValue);

	// 입사날짜가 특정 날짜 이후인 직원들 검색
	// select * from employees where hire_date > ?
	List<Employee> findByHireDateAfter(LocalDate greaterValue);

	// 입사날짜가 날짜 범위 안에 있는 직원들 검색
	// select * from employees where hire_date between ? and ?
	List<Employee> findByHireDateBetween(LocalDate lessValue, LocalDate greaterValue);

	// 부서 이름으로 직원 검색
	/* select * from employees e 
	 * 	left join departments d on e.department_id = d.department_id
	 * 	where d.department_id = ? */
	List<Employee> findByDepartmentDepartmentName(String departmentName);

	// 근무 도시 이름으로 직원 검색
	/* select * from employees e 
	 * 	left join departments d on e.department_id = d.department_id
	 * 	left join locations l on d.location_id = l.location_id 
	 * 	where l.city = ? */
	List<Employee> findByDepartmentLocationCity(String city);

	// 근무 국가 아이디로 직원 검색
	/* select * from employees e 
	 * 	left join departments d on e.department_id = d.department_id
	 * 	left join locations l on d.location_id = l.location_id
	 * 	left join countries c on l.country_id = c.country_id
	 * 	where c.country_id = ? */
	List<Employee> findByDepartmentLocationCountryId(String countryId);
	
	
	/* JPQL(Java Persistence Query Language)
	 * JPA에서 사용하는 "객체 지향(object-oriented) 쿼리 문법
	 * 테이블 이름과 컬럼 이름을 사용해서 쿼리 문장을 작성하는 것이 아니라,
	 * 엔터티 이름과 엔터티 필드 이름을 사용하여 쿼리를 작성하는 문법.
	 * (주의) alias(별명)을 반드시 사용해야 함. */
	@Query("select e from Employee e "
			+ "where upper(e.firstName) like upper('%' || ?1 || '%') "
			+ "or upper(e.lastName) like upper('%' || ?2 || '%')")
	List<Employee> findByName(String firstName, String lastName);
	
	@Query("select e from Employee e "
			+ "where upper(e.firstName) like upper('%' || :keyword || '%') "
			+ "or upper(e.lastName) like upper('%' || :keyword || '%')")
	List<Employee> findByName2(@Param("keyword") String name);
	
	// 특정 도시에 근무하는 직원들 검색
//	@Query("select e from Employee e "
//			+ "whwre e.department.location.city = :city")
//	// 메서드 파라미터 이름과 쿼리의 바인딩 파라미터 이름이 같은 경우 @Param 생략 가능.
//	List<Employee> findByCity(String city);
	
	// 특정 국가에 근무하는 직원들 검색
}
