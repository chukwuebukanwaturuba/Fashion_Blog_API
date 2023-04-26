package com.blog.week9blogapp.repository;

import com.blog.week9blogapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    //List<Comment> findByPost_IdOrderByCreatedAt(Long postId);
}
