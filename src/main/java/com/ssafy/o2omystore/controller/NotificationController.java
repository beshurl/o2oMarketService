package com.ssafy.o2omystore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ssafy.o2omystore.dto.Notification;
import com.ssafy.o2omystore.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Notification API", description = "알림 조회, 삭제 등 알림 관련 API")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "userId path? ?? ??? ?????.")
    @GetMapping("/{userId}")
    public List<Notification> getNotificationsByPath(@PathVariable String userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    @Operation(summary = "{notificationId}에 해당하는 알림을 읽음 처리합니다.")
    @PatchMapping("/{notificationId}")
    public void readNotification(@PathVariable long notificationId) {
        notificationService.markRead(notificationId);
    }

    @Operation(summary = "{notificationId}에 해당하는 알림을 삭제합니다.")
    @DeleteMapping("/delete/{notificationId}")
    public void deleteNotification(@PathVariable long notificationId) {
        notificationService.markDeleted(notificationId);
    }

    @Operation(summary = "userId path? ???? ?? ?? ?? ??? ?????.")
    @GetMapping("/{userId}/unread/count")
    public int countUnreadNotificationsByPath(@PathVariable String userId) {
        return notificationService.countUnreadByUserId(userId);
    }
}
