package com.itwill.springboot3.domain;

import java.time.LocalDate;

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

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
	// JPA/Hibernate는 calmel 표기법의 엔터티 필드 이름을 snake 표기법의 컬럼 이름으로 매핑함.
	// ex) 필드 firstName <==> 컬럼 first_name(FIRST_NAME)
	
	@Id
	@Column(name = "EMPLOYEE_ID")
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private LocalDate hireDate;
	
	@ToString.Exclude
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "JOB_ID")
	private Job job;
	private Double salary;
	private Double commissionPct;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;
	
	@ToString.Exclude
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;
}
