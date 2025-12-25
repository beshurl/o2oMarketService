package com.ssafy.o2omystore.service;

import com.ssafy.o2omystore.dto.User;
import java.util.List;

public interface UserService {

    void registerUser(User user);
    User login(String userId, String password);
    User getUserById(String userId);
    void updateUser(User user);
    void updatePoint(String userId, int point);
    void deleteUser(String userId);

    List<User> getAllUsers();
    
    boolean isUsed(String userId);
}
