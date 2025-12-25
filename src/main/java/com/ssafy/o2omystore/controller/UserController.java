package com.ssafy.o2omystore.controller;

import org.springframework.web.bind.annotation.*;

import com.ssafy.o2omystore.FCM.FCMService;
import com.ssafy.o2omystore.dto.LoginRequest;
import com.ssafy.o2omystore.dto.User;
import com.ssafy.o2omystore.service.UserService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User API", description = "사용자 회원 가입, 로그인 등 회원 관련 API")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final FCMService fcmService;

    public UserController(UserService userService, FCMService fcmService) {
        this.userService = userService;
        this.fcmService = fcmService;
        
    }

    @Operation(summary = "회원 가입")
    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        userService.registerUser(user);
    }
    
    @Operation(summary = "로그인")
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        
        User user = userService.login(
            request.getUserId(),
            request.getPassword()
        );
        

        // 로그인 성공 시 DB에서 FCM 토큰 조회
        String fcmToken = fcmService.getTokenByUserId(user.getUserId());
        
        // 토큰 있으면 로그인 성공 FCM 알림 발송
        if (fcmToken != null && !fcmToken.isBlank()) {
            fcmService.sendMessage(fcmToken, "속보", "현재 마감세일중인 상품이 있습니다."
            		+ "");
        }
                
        return user;        
        }
    
    @Operation(summary = "Get user info by userId")
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @Operation(summary = "모든 사용자의 정보를 반환한다. (관리자용)")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Update user info by userId")
    @PutMapping("/users/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody User user) {
        user.setUserId(userId);
        userService.updateUser(user);
    }

    @Operation(summary = "{userId}에 해당하는 사용자를 삭제합니다.")
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @Operation(summary = "{userId}에 해당하는 아이디가 사용 가능하면 true 아니면 false를 반환한다. ")
    @GetMapping("/{userId}")
    public boolean isUsed(@PathVariable String userId) {
    	
    	return userService.isUsed(userId);
    }

}
