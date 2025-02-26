package com.itwill.springboot4.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itwill.springboot4.domain.Post;
import com.itwill.springboot4.dto.PostCreateDto;
import com.itwill.springboot4.dto.PostItemDto;
import com.itwill.springboot4.dto.PostPageDto;
import com.itwill.springboot4.dto.PostUpdateDto;
import com.itwill.springboot4.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepo;

    public PostPageDto read(int page, int pageSize, int pageBlock, String category, String keyword) {
        int basePage = page - 1;
        Pageable pageable = PageRequest.of(basePage, pageSize, Sort.by("id").descending());

        Page<Post> posts;

        switch (category) {
            case "t":
                // 제목에서 대소문자 구분 없이 검색
                posts = postRepo.findByTitleContainingIgnoreCase(keyword, pageable);
                break;
            case "c":
                // 내용에서 대소문자 구분 없이 검색
                posts = postRepo.findByContentContainingIgnoreCase(keyword, pageable);
                break;
            case "a":
                // 작성자에서 대소문자 구분 없이 검색
                posts = postRepo.findByAuthorContainingIgnoreCase(keyword, pageable);
                break;
            case "tc":
                // 제목과 내용에서 대소문자 구분 없이 검색
                posts = postRepo.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword, pageable);
                break;
            default:
                posts = postRepo.findAll(pageable);
                break;
        }

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
    	Post createdPost = postRepo.save(dto.toEntity());
    	
    	long result = createdPost.getId();
    	
    	return result;
    }

    public void update(PostUpdateDto dto) {
        Post post = postRepo.findById(dto.getId()).orElseThrow();
        log.info("beforePost={}", post);

        Post updatedPost = postRepo.save(post.update(dto.getTitle(), dto.getContent()));
        log.info("updatedPost={}", updatedPost);
    }

    public void delete(long id) {
        postRepo.deleteById(id);
    }
}
