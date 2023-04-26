package com.blog.week9blogapp.repository;

import com.blog.week9blogapp.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<Like, Long> {
}
