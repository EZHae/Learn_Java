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
public class PostUpdateDto {

    private long id;
    private String title;
    private String content;

    public Post toEntity() {
        return Post.builder()
            .id(id)
            .title(title)
            .content(content)
            .build();
    }
}
