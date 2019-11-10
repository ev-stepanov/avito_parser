package com.gd.save_ads.service;

import com.gd.model.entity.Ads;
import com.gd.save_ads.AdsRepository;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;

    public AdsServiceImpl(AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }

    @Override
    public Ads save(Ads ads) {
        return adsRepository.save(ads);
    }

    @Cacheable(value = "findAll")
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

    @Cacheable(
            value = "findById",
            key = "#id"
    )
    @Override
    public Optional<Ads> getById(Long id) {
        return adsRepository.findById(id);
    }
}
