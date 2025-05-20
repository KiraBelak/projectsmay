package com.alldata.mobsell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MobSellApplication {
    public static void main(String[] args) {
        SpringApplication.run(MobSellApplication.class, args);
    }
}
