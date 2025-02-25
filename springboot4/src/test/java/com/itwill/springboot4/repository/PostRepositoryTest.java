package com.itwill.springboot4.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot4.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostRepositoryTest {
	
	@Autowired
	private PostRepository postRepo;
	
	@Test
	public void testFindAll() {
		List<Post> list = postRepo.findAll();
		list.forEach(System.out::println);
	}
	
//	@Test
	public void testSave() {
		// DB 테이블에 저장하기 위한 엔터티 객체 생성.
		Post entity = Post.builder()
				.title("JPA 저장 테스트")
				.content("스프링 부트, JPA를 활용한 엔터티 저장")
				.author("admin")
				.build();
		
		log.info("save 호출 전: {}", entity);
		
		postRepo.save(entity);  // insert 쿼리를 생성하고 실행.
		//-> created_time, modified_time 컬럼에 시간 정보가 자동으로 설정됨.
		
		log.info("save 호출 후: {}", entity);
		//-> "identity"로 설정된 id 필드 값이 리턴된 entity에 설정되어 있음.
	}

}