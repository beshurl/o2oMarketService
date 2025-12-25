package com.ssafy.o2omystore.FCM;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MessagingErrorCode;
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
        } catch (FirebaseMessagingException e) {
            if (e.getMessagingErrorCode() == MessagingErrorCode.UNREGISTERED) {
                fcmTokenDao.deleteToken(fcmToken);
                System.out.println("unregistered fcm token removed");
            } else {
                e.printStackTrace();
                System.out.println("fcm 발송 실패!");
            }
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

    public void sendMessageToAll(String title, String body) {
        List<String> tokens = fcmTokenDao.getAllTokens();
        if (tokens == null || tokens.isEmpty()) {
            return;
        }

        for (String token : tokens) {
            if (token == null || token.isBlank()) {
                continue;
            }
            sendMessage(token, title, body);
        }
    }
    
}
