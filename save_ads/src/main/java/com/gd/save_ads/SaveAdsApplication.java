package com.gd.save_ads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages={"com.gd.save_ads"})
@EntityScan("com.gd.model")
public class SaveAdsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaveAdsApplication.class, args);
    }
}