package com.blog.week9blogapp.controller;

import com.blog.week9blogapp.dto.LikeDTO;
import com.blog.week9blogapp.exception.PostNotFoundException;
import com.blog.week9blogapp.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/post/{post-id}/like_post")
    public ResponseEntity<LikeDTO> likePost(@PathVariable("post-id") Long postId, @RequestBody LikeDTO likeDTO) throws PostNotFoundException {
        LikeDTO likeDTOs = likeService.createPostLike(postId,likeDTO);
        return new ResponseEntity<>(likeDTOs, HttpStatus.OK);
    }
    @PostMapping("/comment/{comment-id}")
    public ResponseEntity<LikeDTO> likeComment(@PathVariable("comment-id") Long commentId) throws PostNotFoundException {
        LikeDTO likeDTO = likeService.createCommentLike(commentId);
        return new ResponseEntity<>(likeDTO, HttpStatus.OK);
    }
}
