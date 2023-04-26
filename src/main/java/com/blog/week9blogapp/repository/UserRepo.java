package com.blog.week9blogapp.repository;

import com.blog.week9blogapp.Enum.Role;
import com.blog.week9blogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByEmailAndPassword(String email, String password);
}
