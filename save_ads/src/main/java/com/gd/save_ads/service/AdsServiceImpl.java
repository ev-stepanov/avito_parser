package com.gd.save_ads.service;

import com.gd.model.entity.Ads;
import com.gd.save_ads.AdsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {
    private AdsRepository adsRepository;

    public AdsServiceImpl(AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }

    @Transactional
    @Override
    public Ads save(Ads ads) {
        return adsRepository.save(ads);
    }

    @Override
    public List<Ads> getAllAds() {
        return adsRepository.findAll();
    }

    @Override
    public List<Ads> getAllAdsByPriseLess(Integer price) {
        return adsRepository.findAllByPriceLessThan(price);
    }

    @Override
    public List<Ads> getAllAdsByPriseMore(Integer price) {
        return adsRepository.findAllByPriceGreaterThan(price);
    }
}
