package com.gd.load_ads;

import com.gd.load_ads.service.AvitoService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static com.gd.load_ads.service.AvitoServiceImpl.COUNT_PAGE;

@Component
public class EntryPoint {
    private static final int N_THREADS = 4;
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

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(N_THREADS);
        final int delta = COUNT_PAGE / N_THREADS;

        for (int i = 0; i < N_THREADS; i++)
        {
            int pageFirst = i * delta + 1;
            executor.execute(()-> {
                try {
                    avitoService.getInformationAboutAds(pageFirst + 1, pageFirst + delta);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
