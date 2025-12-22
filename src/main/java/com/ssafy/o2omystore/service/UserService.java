package com.ssafy.o2omystore.service;

import com.ssafy.o2omystore.dto.User;

public interface UserService {

    void registerUser(User user);
    User login(String userId, String password);
    User getUserById(String userId);
    
    boolean isUsed(String userId);
}
