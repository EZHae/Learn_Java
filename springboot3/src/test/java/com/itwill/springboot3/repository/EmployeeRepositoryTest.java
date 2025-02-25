package com.itwill.springboot3.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.Employee;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private DepartmentRepository deptRepo;
	
//	@Test
	public void testDependencyInjection() {
		assertThat(empRepo).isNotNull();
		log.info("empRepo = {}", empRepo);
	}
	
//	@Test
	public void testCount() {
		// select count(*) from employees
		long count = empRepo.count();
		assertThat(count).isEqualTo(count);
		log.info("count={}", count);
	}
	
//	@Test
	public void testFindAll() {
		// select * from employees
		List<Employee> list = empRepo.findAll();
		assertThat(list.size()).isEqualTo(107);
		log.info("emp(id=100) = {}", list.get(0));
	}
	
//	@Test
//	@Transactional
	public void testFindById() {
		// select * from employees where id = ?
		Employee emp1 = empRepo.findById(101).orElseThrow();
		assertThat(emp1.getFirstName()).isEqualTo("Neena");
		log.info("emp1={}", emp1);
		log.info("emp1.job={}", emp1.getJob());
		log.info("emp1.manager={}", emp1.getManager());
		log.info("emp1.department={}", emp1.getDepartment());
		log.info("emp1.department.manager={}", emp1.getDepartment().getManager());
		log.info("emp1.department.location={}", emp1.getDepartment().getLocation());
		log.info("emp1.department.location.country={}", emp1.getDepartment().getLocation().getCountry());
		log.info("emp1.department.location.country.region={}", emp1.getDepartment().getLocation().getCountry().getRegion());
		
		Department dept1 = deptRepo.findById(emp1.getDepartment().getId()).orElseThrow();
		log.info("▼department of emp1▼");
		log.info("dept1={}", dept1);
		log.info("dept1.manager={}", dept1.getManager());
		log.info("dept1.location={}", dept1.getLocation());
		log.info("dept1.location.country={}", dept1.getLocation().getCountry());
		log.info("dept1.location.country.region={}", dept1.getLocation().getCountry().getRegion());
		
		List<Employee> list = dept1.getEmployees();
		list.forEach((emp) -> log.info("depId=90: {}", emp));
	}
	
//	@Test
//	@Transactional
	public void testJpaQueryMethods() {
		List<Employee> list;
		list = empRepo.findByDepartmentId(100);
		list.forEach((emp) -> System.out.println("departmentId=100: " + emp));
		
		list = empRepo.findByFirstName("Neena");
		list.forEach((emp) -> System.out.println("first_name=Neena: " + emp));
		
		list = empRepo.findByFirstNameIgnoreCase("neena");
		list.forEach((emp) -> System.out.println("first_name=neena: " + emp));
		
		list = empRepo.findByFirstNameContaining("na"); // 자동으로 %na%로 바인딩 해줌
		list.forEach((emp) -> System.out.println("first_name like '%na%': " + emp));
		
		list = empRepo.findByFirstNameLike("%na%"); // 자동으로 바인딩해주지 않기 때문에 직접 사용
		list.forEach((emp) -> System.out.println("first_name like '%na%': " + emp));
	}
	
	@Test
	@Transactional
	public void testJpaQueryMethods2() {
		List<Employee> list;
		list = empRepo.findByFirstNameContainingIgnoreCase("da");
		list.forEach((emp) -> System.out.println(emp));
		
		list = empRepo.findByFirstNameContainingIgnoreCaseOrderByFirstNameDesc("da");
		list.forEach((emp) -> System.out.println(emp));
		
		list = empRepo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("da", "ee");
		list.forEach((emp) -> System.out.println(emp));

		list = empRepo.findBySalaryGreaterThan(20000);
		list.forEach((emp) -> System.out.println(emp));
		
		list = empRepo.findBySalaryLessThan(3000);
		list.forEach((emp) -> System.out.println(emp));
		
		list = empRepo.findBySalaryBetween(2000, 3000);
		list.forEach((emp) -> System.out.println(emp));
		
		LocalDate before = LocalDate.of(2003, 1, 1);
		list = empRepo.findByHireDateBefore(before);
		list.forEach((emp) -> System.out.println(emp));
		
		LocalDate after = LocalDate.of(2004, 1, 1);
		list = empRepo.findByHireDateAfter(after);
		list.forEach((emp) -> System.out.println(emp));
		
		list = empRepo.findByHireDateBetween(before, after);
		list.forEach((emp) -> System.out.println(emp));
		
		list = empRepo.findByDepartmentDepartmentName("Finance");
		list.forEach((emp) -> System.out.println(emp));
		
		list = empRepo.findByDepartmentLocationCity("Toronto");
		list.forEach((emp) -> System.out.println(emp));
		
		list = empRepo.findByDepartmentLocationCountryId("DE");
		list.forEach((emp) -> System.out.println(emp));
		
		list = empRepo.findByName("da", "ee");
		list.forEach((emp) -> System.out.println(emp));
	}
}
