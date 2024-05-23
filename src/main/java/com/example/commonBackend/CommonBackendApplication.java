package com.example.commonBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CommonBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonBackendApplication.class, args);
    }

}
