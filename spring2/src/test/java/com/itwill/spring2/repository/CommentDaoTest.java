package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class CommentDaoTest {
	
	@Autowired
	private CommentDao commentDao;
	
//	@Test
	public void testSelectByPostId() {
		List<Comment> comments = commentDao.selectByPostId(1);
		Assertions.assertNotNull(comments);
		log.debug("# of comments result={}", comments.size());
		comments.forEach(t -> log.debug("commentsByPostId(1)={}", t));
	}
	
	@Test
	public void testInsertComment() {
		Comment comment = Comment.builder().postId(68).username("테스트이름").ctext("테스트내용").build();
		int result = commentDao.insertComment(comment);
		log.debug("insert result={}", result);
	}
	
//	@Test
	public void testDeleteById() {
		int result = commentDao.deleteById(3);
		log.debug("deleteById result={}", result);
	}
	
//	@Test
	public void testDeleteByPostId() {
		int result = commentDao.deleteByPostId(68);
		log.debug("deleteByPostId result={}", result);
	}
	
//	@Test
	public void testUpdateComment() {
		Comment comment = Comment.builder().id(2).ctext("수정됨").build();
		int result = commentDao.updateComment(comment);
		log.debug("comment={}", comment);
		log.debug("updateComment result={}", result);
	}
	
//	@Test
	public void testSelectCommentCount() {
		Integer result = commentDao.selectCommentCount(1);
		log.debug("selectCommentCount result={}", result);
	}
	
//	@Test
	public void testSelectById() {
		Comment c1 = commentDao.selectById(2);
		Assertions.assertNotNull(c1);
		log.debug("c1={}", c1);
		
		Comment c2 = commentDao.selectById(100);
		Assertions.assertNull(c2);
		log.debug("c2={}", c2);
	}
}
