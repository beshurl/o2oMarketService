package com.ssafy.o2omystore.FCM;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FCMService {
    
    private final FCMTokenDao fcmTokenDao;
    
    public FCMService(FCMTokenDao fcmTokenDao) {
        this.fcmTokenDao = fcmTokenDao;
    }
    
    static {
        try {
            Class.forName("com.google.firebase.messaging.Message");
            System.out.println("🔥 Firebase Message class LOADED");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Firebase Message class NOT FOUND");
            e.printStackTrace();
        }
    }


    public void sendMessage(String fcmToken, String title, String body) {
        
        Message message = Message.builder()
            .setToken(fcmToken)
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build()
            )
            .build();
        
        System.out.println(message);

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fcm 발송 실패!");

        }
        
    }

    public void saveToken(String userId, String fcmToken) {
        fcmTokenDao.saveOrUpdate(userId, fcmToken);
    }
    
    public String getTokenByUserId(String userId) {
        return fcmTokenDao.getTokenByUserId(userId);
    }
    
}