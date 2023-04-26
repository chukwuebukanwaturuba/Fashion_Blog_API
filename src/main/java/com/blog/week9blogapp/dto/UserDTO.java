package com.blog.week9blogapp.dto;

import com.blog.week9blogapp.Enum.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {
    //private Long id;
    private String email;
    private String password;
    private Role role;
    private String name;
}
