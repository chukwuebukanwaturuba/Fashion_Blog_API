package com.blog.week9blogapp.dto;

import lombok.Data;

@Data
public class LikeDTO {
    private Long noOfLikes = 0L;
    private Long userId;
    private Boolean checked;
    private Long postId;
    private Long commentId;
}
