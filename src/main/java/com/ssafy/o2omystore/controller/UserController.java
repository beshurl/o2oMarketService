package com.ssafy.o2omystore.controller;

import org.springframework.web.bind.annotation.*;

import com.ssafy.o2omystore.dto.LoginRequest;
import com.ssafy.o2omystore.dto.User;
import com.ssafy.o2omystore.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User API", description = "사용자 회원 가입, 로그인 등 회원 관련 API")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "회원 가입")
    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        userService.registerUser(user);
    }
    
    @Operation(summary = "로그인")
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return userService.login(request.getUserId(), request.getPassword());
    }
}
