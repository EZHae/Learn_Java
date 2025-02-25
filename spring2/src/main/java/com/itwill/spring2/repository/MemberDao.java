package com.itwill.spring2.repository;

import com.itwill.spring2.domain.Member;

public interface MemberDao {
	Member selectByUsername(String username);
	
	Member selectByEmail(String email);
	
	int insertUser(Member member);
	
	Member selectUser(Member member);
}
