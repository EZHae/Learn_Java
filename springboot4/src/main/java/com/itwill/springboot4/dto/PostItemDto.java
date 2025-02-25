package com.itwill.springboot4.dto;

import java.time.LocalDateTime;

import com.itwill.springboot4.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostItemDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime createdTime;

    public static PostItemDto fromEntity(Post post) {

        return PostItemDto.builder()
            .id(post.getId())
            .title(post.getTitle())
            .author(post.getAuthor())
            .createdTime(post.getCreatedTime())
            .build();
    }
}
