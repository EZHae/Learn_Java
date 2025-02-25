package com.itwill.spring2.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// members 테이블의 엔터티 클래스(Domain, Model)
public class Member {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private Integer points;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
}
