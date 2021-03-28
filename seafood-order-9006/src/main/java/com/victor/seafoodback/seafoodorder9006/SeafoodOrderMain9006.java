package com.victor.seafoodback.seafoodorder9006;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SeafoodOrderMain9006 {
    public static void main(String[] args) {
        SpringApplication.run(SeafoodOrderMain9006.class,args);
    }
}
