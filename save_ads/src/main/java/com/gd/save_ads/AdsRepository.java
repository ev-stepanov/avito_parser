package com.gd.save_ads;

import com.gd.model.entity.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Long> {
    List<Ads> findAllByPriceLessThan(Integer price);
    List<Ads> findAllByPriceGreaterThan(Integer price);
}
