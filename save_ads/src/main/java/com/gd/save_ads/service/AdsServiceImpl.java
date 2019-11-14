package com.gd.save_ads.service;

import com.gd.model.entity.Ads;
import com.gd.save_ads.repository.AdsRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

    @Override
    public List<Ads> getAllAds() {
        return adsRepository.findAll();
    }

    public List<Ads> getAllAdsWithNThreads(int countThread) {
        final ExecutorService executorService = Executors.newFixedThreadPool(countThread);

        final long count = adsRepository.count();
        final List<Ads> ads = Collections.synchronizedList(new ArrayList<>());
        List<Future<List<Ads>>> result = new ArrayList<>();
        for (int i = 0; i < countThread; i++) {
            int finalI = i;
            final Future<List<Ads>> submit = executorService.submit(
                    () ->
                            adsRepository.findAll(PageRequest.of(finalI, (int) (count / countThread))).getContent());
            result.add(submit);
        }

        for (Future<List<Ads>> list1 : result) {
            try {
                ads.addAll(list1.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        return ads;
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
