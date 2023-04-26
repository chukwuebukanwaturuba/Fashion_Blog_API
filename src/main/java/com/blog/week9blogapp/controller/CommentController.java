package com.blog.week9blogapp.controller;

import com.blog.week9blogapp.dto.ApiResponse;
import com.blog.week9blogapp.dto.CommentDTO;
import com.blog.week9blogapp.exception.InvalidInputException;
import com.blog.week9blogapp.exception.PostNotFoundException;
import com.blog.week9blogapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{id}/comment")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Long id){
        return new ResponseEntity<>(commentService.createComment(commentDTO, id), HttpStatus.CREATED);
    }

    @PutMapping("post/{id}/update")
    public ResponseEntity<ApiResponse<?>> updateComment(@RequestBody CommentDTO commentDTO, @PathVariable Long id) throws InvalidInputException, PostNotFoundException {
        try {
            commentService.updateComment(commentDTO, id);

        }catch (Exception e){
            e.getMessage();
            return new ResponseEntity<>(new ApiResponse<>("999", "Failed to update", "check you network"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("000", "Updated successful", null), HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}/delete")
    public ResponseEntity<ApiResponse<?>> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteCommentById(id);
            return new ResponseEntity<>(new ApiResponse<>("111", "Comment deleted.", null), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiResponse<>("000", "comment failed to delete", null), HttpStatus.OK);
        }
    }
}
