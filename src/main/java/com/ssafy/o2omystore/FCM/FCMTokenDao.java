package com.ssafy.o2omystore.FCM;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FCMTokenDao {
    
    void saveOrUpdate(String userId, String fcmToken);
    
    String getTokenByUserId(String userId);

    List<String> getAllTokens();

    void deleteToken(String fcmToken);

}
