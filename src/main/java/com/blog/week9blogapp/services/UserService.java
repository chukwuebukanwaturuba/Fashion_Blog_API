package com.blog.week9blogapp.services;

import com.blog.week9blogapp.dto.UserDTO;
import com.blog.week9blogapp.model.User;

import java.util.List;

public interface UserService {

    User registerUser(UserDTO userDTO);
    User registerAdmin(UserDTO userDTO);
    User loginUser(UserDTO userDTO);
    List<User> findAllUser();
    void deleteUserById( Long id);


}
