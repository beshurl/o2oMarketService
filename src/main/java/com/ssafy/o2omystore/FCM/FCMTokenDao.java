package com.ssafy.o2omystore.FCM;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FCMTokenDao {
	
	void saveOrUpdate(String userId, String fcmToken);
	
	String getTokenByUserId(String userId);

}
