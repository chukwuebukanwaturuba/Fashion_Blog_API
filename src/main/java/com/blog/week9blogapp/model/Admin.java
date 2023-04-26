package com.blog.week9blogapp.model;

import com.blog.week9blogapp.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private  String name;
    @Column(nullable = false, unique = true)
    private  String email;

    @Column(nullable = false)
    private String password;
@Enumerated(EnumType.STRING)
    private Role role;
}
