package com.victor.seafoodback.seafoodmedia9005;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SeafoodMediaMain9005 {
    public static void main(String[] args) {
        SpringApplication.run(SeafoodMediaMain9005.class,args);
    }
}
