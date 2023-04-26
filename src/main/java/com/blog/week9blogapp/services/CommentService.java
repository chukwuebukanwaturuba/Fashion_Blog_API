package com.blog.week9blogapp.services;

import com.blog.week9blogapp.dto.CommentDTO;
import com.blog.week9blogapp.exception.InvalidInputException;
import com.blog.week9blogapp.exception.PostNotFoundException;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO, Long id);
    CommentDTO updateComment(CommentDTO commentDTO, Long id) throws InvalidInputException, PostNotFoundException;
    void deleteCommentById(Long id);
}
