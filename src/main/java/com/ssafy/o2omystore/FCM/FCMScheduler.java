package com.ssafy.o2omystore.FCM;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FCMScheduler {

    private final FCMService fcmService;

    public FCMScheduler(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @Scheduled(cron = "0 30 02 * * *", zone = "Asia/Seoul")
    public void sendDailySaleNotification() {
        fcmService.sendMessageToAll("마감 세일 상품 확인하세요!", "마감 세일 상품 확인하세요!");
    }
    
}