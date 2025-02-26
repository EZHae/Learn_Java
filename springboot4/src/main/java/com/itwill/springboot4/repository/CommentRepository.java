package com.itwill.springboot4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot4.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);
}
