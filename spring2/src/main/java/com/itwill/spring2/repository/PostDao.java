package com.itwill.spring2.repository;

import java.util.List;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostSearchDto;

public interface PostDao {
	
	List<Post> selectOrderByIdDesc();
	
	Post selectById(Integer id);
	
	int insertPost(Post post);
	
	int updatePost(Post post);
	
	int deletePost(Integer id);
	
	List<Post> search(PostSearchDto dto);
}
