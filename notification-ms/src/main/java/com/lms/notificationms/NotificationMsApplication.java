package com.lms.notificationms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationMsApplication.class, args);
    }

}
