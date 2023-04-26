package com.blog.week9blogapp.serviceImpl;

import com.blog.week9blogapp.dto.CommentDTO;
import com.blog.week9blogapp.exception.InvalidInputException;
import com.blog.week9blogapp.exception.PostNotFoundException;
import com.blog.week9blogapp.exception.ResourceNotFoundException;
import com.blog.week9blogapp.model.Comment;
import com.blog.week9blogapp.model.Post;
import com.blog.week9blogapp.model.User;
import com.blog.week9blogapp.repository.CommentRepo;
import com.blog.week9blogapp.repository.PostRepo;
import com.blog.week9blogapp.repository.UserRepo;
import com.blog.week9blogapp.services.CommentService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final HttpSession session;
    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo, HttpSession session,
                              PostRepo postRepo, ModelMapper modelMapper,
                              UserRepo userRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.session = session;
        this.userRepo = userRepo;
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Long id) {
        if (session.getAttribute("userId") == null) {
            throw new RuntimeException("Please login to the application.");
        }
        Long userId = (Long) session.getAttribute("userId");
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User does not exist"));
        Post commentPost = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        Comment newComment = modelMapper.map(commentDTO, Comment.class);
        newComment.setPost(commentPost);
        newComment.setUser(user);
        Comment savedComment = commentRepo.save(newComment);

        return modelMapper.map(savedComment, com.blog.week9blogapp.dto.CommentDTO.class);
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO, Long id) throws InvalidInputException, PostNotFoundException {
        if (session.getAttribute("userId") ==null) {
            throw new RuntimeException("Please login to the application");
        }
        if (commentDTO.getBody().equals("")){
            throw new InvalidInputException("Please write a comment");
        }
        Comment existingComment = commentRepo.findById(id).orElseThrow(()-> {
            String message = "Can't find existing comment";
            return new PostNotFoundException(message);
        });
        existingComment.setBody(commentDTO.getBody());
        commentRepo.save(existingComment);
        return modelMapper.map(existingComment, com.blog.week9blogapp.dto.CommentDTO.class);
    }

    @Override
    public void deleteCommentById(Long id) {
        Comment comment = commentRepo.findById(id).orElseThrow(()-> new
                RuntimeException("Please login to the app to execute this function" ));
        commentRepo.delete(comment);
    }
}
