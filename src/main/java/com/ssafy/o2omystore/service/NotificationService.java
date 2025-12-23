package com.ssafy.o2omystore.service;

import java.util.List;

import com.ssafy.o2omystore.dto.Notification;

public interface NotificationService {

    List<Notification> getNotificationsByUserId(String userId);

    void markRead(long notificationId);

    void markDeleted(long notificationId);

    int countUnreadByUserId(String userId);
}
