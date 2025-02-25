package com.itwill.spring2.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// MVC 아키텍처에서 Model 역할을 담당하는 객체.
// 데이터베이스 posts 테이블의 모델.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
	private Integer id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
}
