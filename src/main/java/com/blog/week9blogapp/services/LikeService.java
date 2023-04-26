package com.blog.week9blogapp.services;

import com.blog.week9blogapp.dto.LikeDTO;
import com.blog.week9blogapp.exception.PostNotFoundException;
import com.blog.week9blogapp.model.Comment;
import com.blog.week9blogapp.model.Like;
import com.blog.week9blogapp.model.Post;

public interface LikeService {

    LikeDTO createPostLike(Long postId,LikeDTO likeDTO) throws PostNotFoundException;

    LikeDTO createCommentLike(Long commentId) throws PostNotFoundException;
}
