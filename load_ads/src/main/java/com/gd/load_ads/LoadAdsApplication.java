package com.gd.load_ads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.gd.load_ads", "com.gd.save_ads", "com.gd.model"})
public class LoadAdsApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoadAdsApplication.class, args);
    }
}
