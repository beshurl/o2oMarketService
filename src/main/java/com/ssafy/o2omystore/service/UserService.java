package com.ssafy.o2omystore.service;

import com.ssafy.o2omystore.dto.User;

public interface UserService {

    void registerUser(User user);
    User login(String userId, String password);
    User getUserById(String userId);
    void updateUser(User user);
    void updatePoint(String userId, int point);
    
    boolean isUsed(String userId);
}
