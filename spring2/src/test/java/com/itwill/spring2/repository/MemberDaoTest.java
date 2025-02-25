package com.itwill.spring2.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class MemberDaoTest {
	
	@Autowired
	private MemberDao memberDao;
	
//	@Test
	public void testSelectByUsername() {
		Member m1 = memberDao.selectByUsername("admin");
		Assertions.assertNotNull(m1);
		log.debug("admin={}", m1);
		
		Member m2 = memberDao.selectByUsername("어드민");
		Assertions.assertNull(m2);
		log.debug("어드민={}", m2);
	}
	
	@Test
	public void testSelectByEmail() {
		Member m1 = memberDao.selectByEmail("admin@itwill.com");
		Assertions.assertNotNull(m1);
		log.debug("admin={}", m1);
		
		Member m2 = memberDao.selectByEmail("어드민@아이티윌.컴");
		Assertions.assertNull(m2);
		log.debug("어드민={}", m2);
	}
	
}
