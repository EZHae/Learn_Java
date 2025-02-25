package com.itwill.springboot2.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * ORM(Object Relation Mapping, 객체 관계 매핑)
 * 	객체와 테이블(데이터)를 매핑시키는 기술 스펙.
 * 
 * JPA(Java Persistence API)
 * 	자바에서 ORM을 정의한 기술 스펙.
 * 
 * Hibernate
 * 	JPA를 구현한 프레임워크.
 */

@NoArgsConstructor
@Getter					// 엔터티 클래스가 setter 메서드를 가지고 있지 않아도, JPA/Hibernate 프레임 워크는
						// Reflection을 사용해서 private 필드 값들을 직접 설정할 수 있다.
						/* @Setter, @Data 애너테이션을 사용하지 않는 이유: 
						 * Update문을 사용하는 경우는 흔하지 않고, 필요한 필드, 필요한 경우에만 사용하기 위해 */ 
@ToString
@EqualsAndHashCode
@Entity					// Entity: DB의 테이블에 매핑되는 자바 객체
@Table(name = "EMP")	// Table(name = " ... "): 엔터티 클래스의 이름과 매핑되는 테이블의 이름이 다른 경우 작성
public class Employee {

	@Id		// 테이블에서 Primary Key에 해당하는 경우 작성, Repository 인터페이스를 선언할 때 사용할 ID 타입.
	@Column(name = "EMPNO") // 엔터티 필드 이름과 매핑되는 컬럼 이름과 다른 경우 작성
	private Integer id;
	private String ename;
	private String job;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MGR")
	private Employee manager;
	private LocalDateTime hiredate;
	
	@Column(name = "SAL")
	private Double salary;
	
	@Column(name = "COMM")
	private Double commision;
//	private Integer deptno;
	
	@ToString.Exclude // toString 메서드를 작성할 때 출력에서 제외
	@ManyToOne(fetch = FetchType.LAZY)	// emp 테이블은 dept 테이블과 ManyToOne 관계(relation)
										// FetchType.LAZY: 필요할 때만 조인된 결과 조회
										// FetchType.EAGER: 무조건 함께 조회
	@JoinColumn(name = "DEPTNO") // EMP 테이블과 DEPT 테이블을 join하는 컬럼 이름
	private Department department;
}
