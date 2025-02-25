package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Member;

import lombok.Data;

@Data
public class MemberSignInDto {
	private String username;
	private String password;
	
	public Member toEntity() {
		return Member.builder().username(username).password(password).build();
	}
}
