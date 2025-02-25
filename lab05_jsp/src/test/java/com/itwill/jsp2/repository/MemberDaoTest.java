package com.itwill.jsp2.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Member;

public class MemberDaoTest {
	private static final Logger log = LoggerFactory.getLogger(MemberDaoTest.class);
	private static MemberDao memberDao = MemberDao.INSTANCE;
	
//	@Test
//	public void testInsert() {
//		Member member = Member.builder()
//				.userName("admin")
//				.password("admin1234")
//				.email("admin@itwill.com")
//				.build();
//		int result = memberDao.insert(member);
//		
//		Assertions.assertEquals("1", result);
//		
//		Assertions.assertNotNull(member);
//	}
	
	@Test
	public void testSelect() {
		Member member = memberDao.select("admin", "admin1234");
		Assertions.assertNotNull(member);
		log.debug("member = {}", member);
		
	}
}
