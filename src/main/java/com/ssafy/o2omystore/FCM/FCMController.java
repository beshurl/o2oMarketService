package com.ssafy.o2omystore.FCM;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fcm")
public class FCMController {
	
	private final FCMService fcmService;
	
	public FCMController(FCMService fcmService) {
		this.fcmService = fcmService;
	}
	
	@PostMapping("/token")
	public void saveFcmToken(@RequestBody FCMTokenRequest request) {
        fcmService.saveToken(request.getUserId(), request.getToken());
    }
	
	

}
