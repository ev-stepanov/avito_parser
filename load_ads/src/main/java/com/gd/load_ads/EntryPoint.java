package com.gd.load_ads;

import com.gd.load_ads.service.AvitoService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class EntryPoint {
    private final AvitoService avitoService;

    public EntryPoint(AvitoService avitoService) {
        this.avitoService = avitoService;
    }

    @PostConstruct
    public void main() {
//        try {
//            avitoService.getInformationAboutAds(1, 2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++)
        {
            int finalI = i;

            executor.execute(()-> {
                try {
                    avitoService.getInformationAboutAds(finalI * 8 + 1, finalI + 8);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
