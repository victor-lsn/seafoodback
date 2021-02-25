package com.victor.seafoodback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SeafoodSystem9002 {
    public static void main(String[] args) {
        SpringApplication.run(SeafoodSystem9002.class, args);
    }
}
