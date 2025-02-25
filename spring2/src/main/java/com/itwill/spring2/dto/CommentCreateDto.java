package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Comment;

import lombok.Data;

@Data
public class CommentCreateDto {
	private Integer postId;
	private String username;
	private String ctext;
	
	// DTO -> Entity 변환 메서드
	public Comment toEntity() {
		return Comment.builder()
				.postId(postId).username(username).ctext(ctext).build();
	}
}
