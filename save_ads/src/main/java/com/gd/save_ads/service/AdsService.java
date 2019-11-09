package com.gd.save_ads.service;

import com.gd.model.entity.Ads;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdsService {
    Ads save (Ads ads);
    Ads getById(Long id);
    List<Ads> getAllAds();
    List<Ads> getAllAdsByPriseLess(Integer price);
    List<Ads> getAllAdsByPriseMore(Integer price);
}
