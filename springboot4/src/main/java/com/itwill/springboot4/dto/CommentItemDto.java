package com.itwill.springboot4.dto;

import java.time.LocalDateTime;

import com.itwill.springboot4.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CommentItemDto {

    private Long id;
    private Long postId;
    private String text;
    private String writer;
    private LocalDateTime createdTime;

    public static CommentItemDto fromEntity(Comment comment) {
        return CommentItemDto.builder()
            .id(comment.getId())
            .postId(comment.getPost().getId())
            .text(comment.getText())
            .writer(comment.getWriter())
            .createdTime(comment.getCreatedTime())
            .build();
    }
}
