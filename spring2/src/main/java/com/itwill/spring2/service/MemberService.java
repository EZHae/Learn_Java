package com.itwill.spring2.service;

import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Member;
import com.itwill.spring2.dto.MemberSignInDto;
import com.itwill.spring2.dto.MemberSignUpDto;
import com.itwill.spring2.repository.MemberDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	
	// final 필드와 (필수 아규먼트를 갖는) 생성자를 사용한 의존성 주입
	private final MemberDao memberDao;
	
	// boolean checkUsername(String)
	// 사용 가능한(테이블에 없는) username이면 true, 아니면 false
	public boolean checkUsername(String username) {
		log.debug("checkUsername(username={}", username);
		Member member = memberDao.selectByUsername(username);
		return (member == null);
	}
	
	// boolean checkEmail(String)
	// 사용 가능한(테이블에 없는) email이면 true, 아니면 false
	public boolean checkEmail(String email) {
		log.debug("checkEmail(email={})", email);
		Member member = memberDao.selectByEmail(email);
		return (member == null);
	}
	
	public int create(MemberSignUpDto dto) {
		log.debug("create(member={}", dto);
		int result = memberDao.insertUser(dto.toEntity());
		return result;
	}
	
	public Member signIn(MemberSignInDto dto) {
		log.debug("select(dto={})", dto);
		Member member = memberDao.selectUser(dto.toEntity());
		log.debug("member={}", member);
		return member;
	}

}
