package com.itwill.springboot4.service;

import org.slf4j.helpers.Reporter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itwill.springboot4.domain.Post;
import com.itwill.springboot4.dto.PostCreateDto;
import com.itwill.springboot4.dto.PostItemDto;
import com.itwill.springboot4.dto.PostPageDto;
import com.itwill.springboot4.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepo;

    public PostPageDto read(int page, int pageSize, int pageBlock) {
        int basePage = page - 1;
        Pageable pageable = PageRequest.of(basePage, pageSize, Sort.by("id").descending());
        Page<Post> posts = postRepo.findAll(pageable);

        Page<PostItemDto> postDto = posts.map((post) -> PostItemDto.fromEntity(post));
        postDto.forEach((postItemDto) -> log.info("{}", postItemDto));

        int totalPages = posts.getTotalPages();
        int currentPage = posts.getNumber() + 1;
        int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
        int endPage = Math.min(startPage + pageBlock - 1, totalPages);

        return new PostPageDto(postDto, currentPage, totalPages, startPage, endPage);
    }
    
    public Post read(long id) {
    	Post post = postRepo.findById(id).orElseThrow();
    	
    	return post;
    }
    
    public long create(PostCreateDto dto) {
    	Post post = Post.builder()
    			.title(dto.getTitle())
    			.content(dto.getContent())
    			.author(dto.getAuthor())
    			.build();
    	
    	Post createdPost = postRepo.save(post);
    	
    	long result = createdPost.getId();
    	
    	return result;
    }
}
