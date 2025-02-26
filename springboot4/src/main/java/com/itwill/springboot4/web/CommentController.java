package com.itwill.springboot4.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.springboot4.domain.Post;
import com.itwill.springboot4.dto.CommentCreateDto;
import com.itwill.springboot4.dto.CommentItemDto;
import com.itwill.springboot4.service.CommentService;
import com.itwill.springboot4.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;




@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentItemDto>> read(@PathVariable(name = "postId") long postId) {
        List<CommentItemDto> comments = commentService.read(postId);

        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<?> create(@PathVariable(name = "postId") long postId, @RequestBody CommentCreateDto dto) {
        Post post = postService.read(postId);

        log.info("Beforedto={}", dto);
        dto.setPost(post);
        log.info("Afterdto={}", dto);

        commentService.create(dto);
        
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> create(@PathVariable(name = "id") long id) {

        commentService.delete(id);

        return ResponseEntity.ok().build();
    }
    
}
