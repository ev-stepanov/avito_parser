package com.gd.save_ads.service;

import com.gd.model.entity.Ads;
import org.springframework.stereotype.Service;

@Service
public interface AdsService {
    Ads save (Ads ads);
}
