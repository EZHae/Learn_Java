package com.itwill.springboot4.dto;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostPageDto {

    private Page<PostItemDto> post;
    private int currentPage;
    private int totalPages;
    private int startPage;
    private int endPage;
}
