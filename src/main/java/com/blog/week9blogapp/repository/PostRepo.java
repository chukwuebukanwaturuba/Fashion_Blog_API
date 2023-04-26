package com.blog.week9blogapp.repository;

import com.blog.week9blogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {

}
