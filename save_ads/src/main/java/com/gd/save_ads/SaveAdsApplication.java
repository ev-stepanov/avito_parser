package com.gd.save_ads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages={"com.gd.save_ads"})
@EntityScan("com.gd.model")
@EnableCaching
public class SaveAdsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaveAdsApplication.class, args);
    }
}
