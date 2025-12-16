package com.ssafy.o2omystore.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @GetMapping
    public void getNotifications() {
    }

    @PatchMapping("/{notificationId}")
    public void readNotification(@PathVariable int notificationId) {
    }
}
