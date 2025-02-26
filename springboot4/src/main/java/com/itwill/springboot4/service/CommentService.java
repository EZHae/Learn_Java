package com.itwill.springboot4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot4.domain.Comment;
import com.itwill.springboot4.dto.CommentCreateDto;
import com.itwill.springboot4.dto.CommentItemDto;
import com.itwill.springboot4.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository comRepo;
    
    public List<CommentItemDto> read(long postId) {
        List<Comment> list = comRepo.findByPostId(postId);

        List<CommentItemDto> comments = list.stream().map(CommentItemDto::fromEntity).toList();

        return comments;
    }

    public void create(CommentCreateDto dto) {
        comRepo.save(dto.toEntity());
    }

    public void delete(long id) {
        comRepo.deleteById(id);
    }
}
