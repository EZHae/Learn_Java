package com.itwill.spring2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Comment;
import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.dto.CommentUpdateDto;
import com.itwill.spring2.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
	
	// final 필드와 필스 아규먼트를 갖는 생성자(@RequiredArgsContructor)를 사용한 의존성 주입
	private final CommentDao commentDao;
	
	// 해당 아이디의 댓글 1개를 검색하는 서비스
	public CommentItemDto readById(Integer id) {
		log.debug("readById(id={})", id);
		
		// 영속성 계층의 메서드를 호출해서 select 쿼리를 실행.
		Comment comment = commentDao.selectById(id);
		
		return CommentItemDto.fromEntity(comment);
	}
	
	// 특정 포스트에 달려 있는 모든 댓글을 검색하는 서비스
	public List<CommentItemDto> readByPostId(Integer postId) {
		log.debug("readByPostId(postId={})", postId);
		
		List<Comment> list = commentDao.selectByPostId(postId);
		
//		방법 1.
//		List<CommentItemDto> result = new ArrayList<CommentItemDto>();
//		for(Comment c : list) {
//			result.add(CommentItemDto.fromEntity(c));
//		}
//		return result;
		
//		방법 2.
//		return list.stream().map(x -> CommentItemDto.fromEntity(x)).toList();
		
//		방법 3.
		return list.stream().map(CommentItemDto::fromEntity).toList();
	}
	
	// 특정 포스트에 댓글을 추가하는 서비스
	public int create(CommentCreateDto dto) {
		log.debug("create(dto={})", dto);
		
		int result = commentDao.insertComment(dto.toEntity());
		
		return result;
	}
	
	// 아이디가 일치하는 댓글을 삭제하는 서비스
	public int delete(Integer id) {
		log.debug("delete(id={})", id);
		
		int result = commentDao.deleteById(id);
		
		return result;
	}
	
	public int update(CommentUpdateDto dto) {
		log.debug("update(dto={})", dto);
		
		int result = commentDao.updateComment(dto.toEntity());
		
		return result;
	}
	
}
