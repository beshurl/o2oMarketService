package com.ssafy.o2omystore.controller;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Notification API", description = "알림 조회, 삭제 등 알림 관련 API")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Operation(summary = "{userId}에 해당 하는 사용자의 전체 알림을 반환합니다.")
    @GetMapping
    public void getNotifications(@PathVariable String userId) {
    }

    @Operation(summary = "{notificationId}에 해당하는 알림을 읽음 처리합니다.")
    @PatchMapping("/{notificationId}")
    public void readNotification(@PathVariable int notificationId) {
    }
    
    @Operation(summary = "{notificationId}에 해당하는 알림을 삭제합니다.")
    @DeleteMapping("/delete/{notificationId}")
    public void deleteNotification(@PathVariable int notificationId) {
    }
}
