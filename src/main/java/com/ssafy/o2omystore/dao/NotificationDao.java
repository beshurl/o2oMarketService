package com.ssafy.o2omystore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.o2omystore.dto.Notification;

@Mapper
public interface NotificationDao {

    List<Notification> selectNotificationsByUserId(String userId);

    int markRead(long notificationId);

    int markDeleted(long notificationId);

    int countUnreadByUserId(String userId);
}
