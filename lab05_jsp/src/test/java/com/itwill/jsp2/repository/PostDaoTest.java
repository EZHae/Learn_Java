package com.itwill.jsp2.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;

public class PostDaoTest {
	private static final Logger log = LoggerFactory.getLogger(PostDaoTest.class);
	private final PostDao postDao = PostDao.INSTANCE;
	
	@Test
	public void testSelect() {
		List<Post> posts =  postDao.select();
		for (Post post : posts) {
			log.info(post.toString());
		}
	}
}
