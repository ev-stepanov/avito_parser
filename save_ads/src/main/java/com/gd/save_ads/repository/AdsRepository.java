package com.gd.save_ads.repository;

import com.gd.model.entity.Ads;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Long> {
    List<Ads> findAllByPriceLessThan(Integer price);
    List<Ads> findAllByPriceGreaterThan(Integer price);
    List<Ads> findAllByPrice(Integer price);
    List<Ads> findAllByPrice(Integer price, Pageable pageable);
}
