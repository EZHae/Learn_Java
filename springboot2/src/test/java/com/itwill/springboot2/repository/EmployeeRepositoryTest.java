package com.itwill.springboot2.repository;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot2.domain.Employee;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository empRepo;
	
//	@Test
//	public void test() {
//		assertThat(empRepo).isNotNull();
//		log.info("empRepo={}", empRepo);
//	}
//	
	@Transactional
	@Test
	public void testFindAll() {
		List<Employee> list =  empRepo.findAll();
		assertThat(list.size()).isEqualTo(14);
		list.forEach((emp) -> {
			System.out.println(emp);
			System.out.println(emp.getDepartment());
		});
	}
//	
//	@Test
//	public void testFindByEname() {
//		// select * from emp where ename = ?
//		List<Employee> list = empRepo.findByEnameIgnoreCase("ward");
//		assertThat(list).isNotEmpty();
//		list.forEach((emp) -> log.info("{}", emp));
//	}
//	
//	@Test
//	public void testFindByEnameContainingIgnoreCase () {
//		// select * from emp where lower(enmae) = like lower(%?%)
//		List<Employee> list = empRepo.findByEnameContainingIgnoreCase("e");
//		assertThat(list).isNotEmpty();
//		list.forEach((emp) -> log.info("{}", emp));
//	}
//	
//	@Test
//	public void testUpdateJob () throws NoSuchFieldException, IllegalAccessException {
//		Employee employee = empRepo.findById(7900).orElseThrow();
//		assertThat(employee).isNotNull();
//		
//		// job 필드에 접근
//		Field jobField = Employee.class.getDeclaredField("job");
//		
//		// private 필드에 접근 가능하게 설정
//		jobField.setAccessible(true);
//		
//		// 필드 값 수정
//		jobField.set(employee, "CLERK1"); // CLERK -> CLERK1
//		
//		// 수정 엔터티 저장 (update문 호출)
//		empRepo.save(employee);
//	}
//	
//	@Test
//	public void testFindById() {
//		// select * from emp where id = ?
//		// findById은 Optional<T>을 리턴함.
//		// Optional<T>.orElseThrow(): 데이터가 있으면 T 타입의 객체를 리턴
//		// 데이터가 없으면 예외(Exception)을 발생시킴.
//		Employee emp1 = empRepo.findById(7788).orElseThrow();
//		assertThat(emp1.getEname()).isEqualTo("SCOTT");
//		log.info("emp1={}", emp1);
//		log.info("dept={}", emp1.getDepartment());
//		
//		// Optional<T>.orElse(T other): 데이터가 있으면 T타입의 객체를 리턴.
//		// 데이터가 없으면 아규먼트로 전달된 other 객체를 리턴.
//		Employee emp2 = empRepo.findById(1000).orElse(null);
//		assertThat(emp2).isNull();
//		log.info("emp2={}", emp2);
//		
//		// Optonal<T>.orElseGet(Supplier fn): 데이터가 있으면 T타입의 객체를 리턴.
//		// 데이터가 없으면 람다 표현식 fn에서 리턴하는 객체를 리턴.
//		Employee emp3 = empRepo.findById(1000).orElseGet(() -> null);
//		assertThat(emp3).isNull();
//		log.info("emp3={}", emp3);
//	}
}