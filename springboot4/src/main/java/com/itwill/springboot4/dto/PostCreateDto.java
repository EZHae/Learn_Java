package com.itwill.springboot4.dto;

import com.itwill.springboot4.domain.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PostCreateDto {
	
	private String title;
	private String content;
	private String author;

	public Post toEntity() {
		return Post.builder()
			.title(title)
			.content(content)
			.author(author)
			.build();
	}
}
