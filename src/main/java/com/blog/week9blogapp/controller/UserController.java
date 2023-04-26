package com.blog.week9blogapp.controller;

import com.blog.week9blogapp.dto.ApiResponse;
import com.blog.week9blogapp.dto.UserDTO;
import com.blog.week9blogapp.model.User;
import com.blog.week9blogapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User signUpUser(@RequestBody UserDTO userDTO){

        return userService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody UserDTO userDTO,
                          HttpServletRequest request){
        User user = userService.loginUser(userDTO);
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getUserId());
        return user;
    }

    @GetMapping("/allUsers")
    public List<User> getAllUser(){
        return userService.findAllUser();
    }


    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable("userid") Long id) {
        try {
            userService.deleteUserById(id);
            return  new ResponseEntity<>(new ApiResponse<>("000","deleted successfully",null),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new ApiResponse<>("999","failed to delete",null),HttpStatus.OK);
        }
    }

}
