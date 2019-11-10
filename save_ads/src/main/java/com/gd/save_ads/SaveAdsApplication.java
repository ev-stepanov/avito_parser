package com.gd.save_ads;

import com.gd.model.entity.Ads;
import com.gd.save_ads.service.AdsService;
import com.gd.save_ads.service.HibernateSearchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;

@SpringBootApplication(scanBasePackages={"com.gd.save_ads"})
@EntityScan("com.gd.model")
@EnableCaching
public class SaveAdsApplication {
    private static HibernateSearchService searchService;
    private static AdsService adsService;

    public SaveAdsApplication(AdsService adsService, HibernateSearchService searchService) {
        SaveAdsApplication.adsService = adsService;
        SaveAdsApplication.searchService = searchService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SaveAdsApplication.class, args);
        run();
    }

    private static void run() {
        final long l = System.currentTimeMillis();
        final List<Ads> ads = searchService.fuzzySearch("40");
        final long l1 = System.currentTimeMillis();

        System.out.println(ads.size());

    }

}
