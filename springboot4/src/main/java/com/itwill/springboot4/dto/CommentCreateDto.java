package com.itwill.springboot4.dto;

import com.itwill.springboot4.domain.Comment;
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
public class CommentCreateDto {

    private Post post;
    private String writer;
    private String text;

    public Comment toEntity() {
        return Comment.builder()
            .post(post)
            .writer(writer)
            .text(text)
            .build();
    }
}
