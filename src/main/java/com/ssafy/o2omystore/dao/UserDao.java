package com.ssafy.o2omystore.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.o2omystore.dto.User;

@Mapper
public interface UserDao {

    User selectUserById(String userId);

    int insertUser(User user);

    User selectUserForLogin(String userId);
    
    int isUsed(String userId);

    int updatePoint(String userId, int point);
}
