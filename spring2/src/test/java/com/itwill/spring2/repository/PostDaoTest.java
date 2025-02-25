package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class PostDaoTest {
	
	@Autowired // MyBatis 프레임워크에서 자동으로 생성된 PostDao 인터페이스 구현 객체를 주입.
	private PostDao postDao;
	
//	@Test
	public void testPostDao() {
		Assertions.assertNotNull(postDao);
		log.debug("postDao={}", postDao);
	}
	
//	@Test
	public void testSelectOrderByIdDesc() {
		List<Post> list = postDao.selectOrderByIdDesc();
		list.forEach(x -> log.debug("{}", x));
	}
	
//	@Test
	public void testSelectById() {
		// 테이블에 id가 존재하는 경우:
		Post post1 = postDao.selectById(26);
		Assertions.assertNotNull(post1);
		log.debug("post1={}", post1);
		
		// 테이블에 id가 존재하지 않는 경우:
		Post post2 = postDao.selectById(27);
		Assertions.assertNull(post2);
		log.debug("post2={}", post2);
	}
	
//	@Test
	public void testInsertPost() {
		Post post = Post.builder().title("testTitle").content("testContent").author("testAuthor").build();
		int result = postDao.insertPost(post);
		Assertions.assertEquals(1, result);
		log.debug("result={}", result);
	}
	
//	@Test
	public void testUpdatePost() {
		Post post = Post.builder().id(81).title("테스트제목").content("테스트내용").build();
		int result = postDao.updatePost(post);
		Assertions.assertEquals(1, result);
		log.debug("result={}", result);
	}
	
	@Test
	public void testDeletePost() {
		int result = postDao.deletePost(28);
		Assertions.assertEquals(1, result);
		log.debug("result={}", result);
	}
}
