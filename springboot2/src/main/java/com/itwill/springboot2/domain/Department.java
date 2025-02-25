package com.itwill.springboot2.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "DEPT")
public class Department {

	@Id
	@Column(name = "DEPTNO")
	private Integer id;
	private String dname;
	
	@Column(name = "LOC")
	private String location;
	
	@ToString.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	// mappedBy: Employee 엔터티에서 @ManyToOne 애너테이션이 설정된 "필드 이름"으로 설정.
	private List<Employee> employees;
	// DB에는 없는 컬럼을 필드로 만들었음.
	// OneToMany가 설정되어 있기 때문에 조건에 맞는 Employee 객체들이 List에 저장됨.
}
