package com.gd.save_ads.service;

import com.gd.save_ads.AdsRepository;
import com.gd.model.entity.Ads;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;

    public AdsServiceImpl(AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }

    @Transactional
    public Ads save(Ads ads) {
        return adsRepository.save(ads);
    }
}
