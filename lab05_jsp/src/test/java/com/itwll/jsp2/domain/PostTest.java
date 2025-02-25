package com.itwll.jsp2.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;

public class PostTest {
	private static final Logger log = LoggerFactory.getLogger(PostTest.class);
	
	@Test
	public void testPostBuilder() {
		Post post = Post.builder()
				.id(1)
				.title("testTitle")
				.content("testContent")
				.author("testAuthor")
				.createdTime(LocalDateTime.now())
				.modifiedTime(LocalDateTime.now())
				.build();
		
		// 단위 테스트의 성공 케이스를 작성.
		Assertions.assertNotNull(post); // post 객체가 null이 아니면 단위 테스트 성공.
		
		// Assertions.assertEquals(expected(기댓값), actual(실제값))
		Assertions.assertEquals(1, post.getId()); // post 객체의 id가 1과 같으면 단위 테스트 성공.
		Assertions.assertEquals("testTitle", post.getTitle()); // post 객체의 id가 "testTitle"과 같으면 단위 테스트 성공.
		
		log.debug("post = {}", post);
	}
}
