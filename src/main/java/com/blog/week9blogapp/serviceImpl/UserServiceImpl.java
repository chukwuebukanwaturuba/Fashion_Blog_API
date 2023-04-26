package com.blog.week9blogapp.serviceImpl;

import com.blog.week9blogapp.Enum.Role;
import com.blog.week9blogapp.dto.UserDTO;
import com.blog.week9blogapp.model.User;
import com.blog.week9blogapp.repository.UserRepo;
import com.blog.week9blogapp.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Override
    public User registerUser(UserDTO userDTO) {
        if (userDTO.getRole() == null || userDTO.getRole().equals(Role.USER)) {
            userDTO.setRole(Role.USER);
        }
        log.info("About to create a user");
        User user =  User.builder()
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
               .role(userDTO.getRole())
                .build();
        log.info("About to save");
        return userRepo.save(user);
    }





    @Override
    public User registerAdmin(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(Role.ADMIN)
                .build();
        return userRepo.save(user);
    }

    @Override
    public User loginUser(UserDTO userDTO) {
        return userRepo.findUserByEmailAndPassword(userDTO.getEmail(),
                userDTO.getPassword());
                //  userDTO.getRole());
    }


    @Override
    public List<User> findAllUser() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @Override
    public void deleteUserById( Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("No such user with id: "+ id));
        userRepo.delete(user);
    }

}
