package com.blog.week9blogapp.dto;

import com.blog.week9blogapp.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private  String name;
    private  String email;
    private String password;
    private Role role;
}
