package com.gd.save_ads;

import com.gd.model.entity.Ads;
import com.gd.save_ads.service.AdsService;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class LoadDataFromDbBenchmarkTest extends AbstractBenchmark {

    private static AdsService adsService;

    @Autowired
    void setDslContext(AdsService adsService) {
        LoadDataFromDbBenchmarkTest.adsService = adsService;
    }

    @Benchmark
    public void testPerformanceAllAds(Blackhole bh) {

        assert(adsService != null);

        final List<Ads> allAds = adsService.getAllAds();
        bh.consume(allAds.size());
    }
    @Benchmark
    public void testPerformanceAllAdsInMultiThread(Blackhole bh) {

        assert(adsService != null);

        final List<Ads> allAds = adsService.getAllAdsWithNThreads(4);
        bh.consume(allAds.size());
    }
}
