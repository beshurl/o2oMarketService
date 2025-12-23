package com.ssafy.o2omystore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.o2omystore.dao.NotificationDao;
import com.ssafy.o2omystore.dto.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationDao notificationDao;

    public NotificationServiceImpl(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    @Override
    public List<Notification> getNotificationsByUserId(String userId) {
        return notificationDao.selectNotificationsByUserId(userId);
    }

    @Override
    public void markRead(long notificationId) {
        notificationDao.markRead(notificationId);
    }

    @Override
    public void markDeleted(long notificationId) {
        notificationDao.markDeleted(notificationId);
    }

    @Override
    public int countUnreadByUserId(String userId) {
        return notificationDao.countUnreadByUserId(userId);
    }

    @Override
    public void createNotification(Notification notification) {
        notificationDao.insertNotification(notification);
    }
}
