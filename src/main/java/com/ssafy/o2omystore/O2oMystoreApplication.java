package com.ssafy.o2omystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class O2oMystoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(O2oMystoreApplication.class, args);
    }
}
