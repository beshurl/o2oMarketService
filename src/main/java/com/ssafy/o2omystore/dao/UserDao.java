package com.ssafy.o2omystore.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.o2omystore.dto.User;
import java.util.List;

@Mapper
public interface UserDao {

    User selectUserById(String userId);

    int insertUser(User user);

    User selectUserForLogin(String userId);
    
    int isUsed(String userId);

    int updateUser(User user);

    int updatePoint(@Param("userId") String userId, @Param("point") int point);

    int deletePointHistoryByUserId(String userId);
    int deletePointsByUserId(String userId);
    int deleteCouponsByUserId(String userId);
    int deleteUserFcmTokenByUserId(String userId);
    int deleteUserById(String userId);

    List<User> selectAllUsers();
}
