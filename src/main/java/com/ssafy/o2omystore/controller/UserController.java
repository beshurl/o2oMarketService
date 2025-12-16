package com.ssafy.o2omystore.controller;

import org.springframework.web.bind.annotation.*;

import com.ssafy.o2omystore.dto.LoginRequest;
import com.ssafy.o2omystore.dto.User;
import com.ssafy.o2omystore.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        userService.registerUser(user);
    }
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return userService.login(request.getUserId(), request.getPassword());
    }
}
