package com.blog.week9blogapp.services;

import com.blog.week9blogapp.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Long id);
    List<PostDTO> getAllPosts();
    PostDTO getPostById(Long id);
    PostDTO updatePost(PostDTO postDTO, long id);
    void deletePostById(long id);
}
