package com.gd.save_ads;

import com.gd.model.entity.Ads;
import com.gd.save_ads.service.AdsService;
import com.gd.save_ads.service.HibernateSearchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages={"com.gd.save_ads"})
@EntityScan("com.gd.model")
@EnableCaching
public class SaveAdsApplication {
    public static ForkJoinPool forkJoinPool = new ForkJoinPool(4);
    private static AdsRepository adsRepository;
    private static HibernateSearchService searchService;
    private static AdsService adsService;

    public SaveAdsApplication(AdsService adsService, HibernateSearchService searchService, AdsRepository adsRepository) {
        SaveAdsApplication.adsService = adsService;
        SaveAdsApplication.searchService = searchService;
        SaveAdsApplication.adsRepository = adsRepository;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(SaveAdsApplication.class, args);
    }
}
