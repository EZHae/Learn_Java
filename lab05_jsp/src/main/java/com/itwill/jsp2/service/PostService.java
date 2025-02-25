package com.itwill.jsp2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.repository.MemberDao;
import com.itwill.jsp2.repository.PostDao;

// Web MVC 아키텍쳐에서 서비스/비즈니스(Service/Business Layer)을 담당하는 객체.
// 영속성 계층의 기능들을 사용해서 비즈니스 로직을 구현하는 객체.
public enum PostService {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(PostService.class);
	private final PostDao postDao = PostDao.INSTANCE;
	private final MemberDao memberDao = MemberDao.INSTANCE;
	
	public List<Post> read() {
		log.debug("read()");
		return postDao.select();
	}
	
	public Post read(int id) {
		log.debug("read({})", id);
		
		return postDao.select(id);
	}
	
	public List<Post> search(String category, String keyword) {
		log.debug("search()");
		return postDao.select(category, keyword);
	}
	
	public int create(Post post) {
		log.info("create({})", post);
		
		int result = postDao.insert(post);
		log.debug("insert result = {}", result);
		
		// 포스트 작성자에게 10포인트 지급
		result = memberDao.updatePoint(10, post.getAuthor());
		log.debug("update result = {}", result);
		
		return result;
	}
	
	public int update(Post post) {		
		int result = postDao.update(post);
		log.debug("{}개 행이 업데이트됨", result);
		
		return result;
	}
	
	public int delete(int id) {
		log.debug("delete({})", id);
		
		int result = postDao.delete(id);
		log.debug("{}개 행이 삭제됨.", result);
		
		return result;
	}
}
