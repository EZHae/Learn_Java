package com.itwill.springboot4.repository;

import java.util.ArrayList;
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
//	public void testSave() {
//		// DB 테이블에 저장하기 위한 엔터티 객체 생성.
//		Post entity = Post.builder()
//				.title("JPA 저장 테스트")
//				.content("스프링 부트, JPA를 활용한 엔터티 저장")
//				.author("admin")
//				.build();
//		
//		log.info("save 호출 전: {}", entity);
//		
//		postRepo.save(entity);  // insert 쿼리를 생성하고 실행.
//		//-> created_time, modified_time 컬럼에 시간 정보가 자동으로 설정됨.
//		
//		log.info("save 호출 후: {}", entity);
//		//-> "identity"로 설정된 id 필드 값이 리턴된 entity에 설정되어 있음.
//	}
//	
//	@Test
//	public void testUpdate() {
//		Post post = postRepo.findById(21L).orElseThrow();
//		log.info("findById(22) = {}", post);
//		
//		post.update("updateTest2", "업데이트 테스트2");
//		log.info("수정된 post = {}", post);
//		
//		Post saved = postRepo.save(post);
//		log.info("저장된 post = {}", saved);
//		
//		/* JUnit 테스트에서 업데이트 쿼리를 실행하기 위해서는 반드시
//		 * JpaRepository<T, ID>.save(T entity) 메서드를 호출해야 하지만,
//		 * 서비스 계층의 메서드에서는 @Transactional 애너테이션을 설정하면
//		 * save() 메서드를 호출하지 않더라도 업데이트 쿼리가 실행됨. */
//	}
//	
//	@Test
//	public void testDelete() {
//		postRepo.deleteById(21L);
//		//-> select 쿼리 실행 -> delete 쿼리 실행.
//	}
	
	@Test // 더미 데이터 생성
	public void makeDummyData() {
		List<Post> list = new ArrayList<Post>();
		for (int i = 51; i <= 70; i ++) {
			Post post = Post.builder()
					.title("dummy title #" + i)
					.content("더미 데이터 내용 #" + i)
					.author("admin")
					.build();
			list.add(post);
		}
		postRepo.saveAll(list);
	}

}