package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Post;

import lombok.Data;

@Data
public class PostUpdateDto {
	// 필드 이름은 요청 파라미터(양식 데이터의 이름)와 동일하게.
	private Integer id;
	private String title;
	private String content;
	
	// DTO를 Entity로 변환하는 편의 메서드
	public Post toEntity() {
		return Post.builder().id(id).title(title).content(content).build();
	}
}
