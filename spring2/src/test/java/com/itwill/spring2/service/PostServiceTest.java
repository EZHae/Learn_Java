package com.itwill.spring2.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.repository.PostDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class PostServiceTest {
	
	/* 단위 테스트에서 단위 테스트 객체를 생성하고 테스트 메서드를 호출하는 테스트 메인 쓰레드는
	 * 단위 테스트 클래스(PostServiceTest)의 기본 생성자만 호출.
	 * 	-> 아규먼트를 갖는 생성자 호출 불가능
	 * 	-> 애너테이션을 사용한 의존성 주입만 가능. */
	
	@Autowired private PostService postService;
	
//	@Test
	public void testRead() {
		Assertions.assertNotNull(postService);
		
		List<Post> list = postService.read();
		
		list.forEach(x -> log.debug("{}", x));
	}
	
//	@Test
	public void testRead2() {
		Post post = postService.read(81);
		log.debug("post={}", post);
	}
	
	@Test
	public void testUpdate() {
		Post post = Post.builder().id(81).title("testTitle").content("testContent").build();
		
//		int result = postService.update(post.to);
//		log.debug("result={}, updatedPost={}", result, postService.read(post.getId()));
	}
}
