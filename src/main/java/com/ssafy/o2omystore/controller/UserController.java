package com.ssafy.o2omystore.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{userId}")
    public void getUser(@PathVariable String userId) {
    }
}
