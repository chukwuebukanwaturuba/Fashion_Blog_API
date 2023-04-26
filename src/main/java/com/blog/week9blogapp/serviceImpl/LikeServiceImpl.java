package com.blog.week9blogapp.serviceImpl;

import com.blog.week9blogapp.dto.LikeDTO;
import com.blog.week9blogapp.exception.PostNotFoundException;
import com.blog.week9blogapp.model.Comment;
import com.blog.week9blogapp.model.Like;
import com.blog.week9blogapp.model.Post;
import com.blog.week9blogapp.model.User;
import com.blog.week9blogapp.repository.CommentRepo;
import com.blog.week9blogapp.repository.LikeRepo;
import com.blog.week9blogapp.repository.PostRepo;
import com.blog.week9blogapp.repository.UserRepo;
import com.blog.week9blogapp.services.LikeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepo likeRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final CommentRepo commentRepo;
    private final HttpSession session;

    @Override
    public LikeDTO createPostLike(Long postId,LikeDTO likeDTO) throws PostNotFoundException, RuntimeException {
        Long userId = getLoggedInUser();
        if (session.getAttribute("userId") == null) {
            throw new RuntimeException("Please login to the application");
        }
        Post foundPost = postRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
        User foundUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Like like = new Like();
        like.setChecked(likeDTO.getChecked());
        like.setPost(foundPost);
        like.setUser(foundUser);
        likeRepo.save(like);
        return mapToDTO(like);
    }
    @Override
    public LikeDTO createCommentLike(Long commentId) throws RuntimeException {
        Long userId = getLoggedInUser();
        if (session.getAttribute("userId") == null) {
            throw new RuntimeException("Please login to the application");
        }
        Comment foundComment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        User foundUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Like like = new Like();
        like.setComment(foundComment);
        like.setUser(foundUser);
        likeRepo.save(like);
        return mapToDTO(like);
    }
    private Long getLoggedInUser() {
        return (Long) session.getAttribute("userId");
    }
    private LikeDTO mapToDTO(Like like){
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setPostId(like.getPost().getId());
        likeDTO.setUserId(like.getUser().getUserId());
//        likeDTO.setCommentId(like.getComment().getId());
        return likeDTO;
    }
}
