package com.gd.load_ads;

import com.gd.load_ads.service.AvitoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static com.gd.load_ads.service.AvitoServiceImpl.COUNT_PAGE;

@Component
public class EntryPoint {
    private static final int N_THREADS = 4;
    @Value("${custom.page}")
    private Integer CUSTOM_COUNT_PAGE;

    private final AvitoService avitoService;

    public EntryPoint(AvitoService avitoService) {
        this.avitoService = avitoService;
    }

    @PostConstruct
    public void main() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(N_THREADS);
        final Integer count_page = (CUSTOM_COUNT_PAGE != null) ? CUSTOM_COUNT_PAGE : COUNT_PAGE;
        final int div = count_page / N_THREADS;
        final int delta = (div == 0) ? 1 : div;

        int pageFirst = 0;
        if (div > 0) {
            for (int i = 0; i < N_THREADS - 1; i++) {
                pageFirst = i * delta + 1;
                int finalPageFirst = pageFirst;
                executor.execute(() -> avitoService.getInformationAboutAds(finalPageFirst, finalPageFirst + delta));
            }
        }
        int finalPageFirst1 = pageFirst;
        executor.execute(()-> avitoService.getInformationAboutAds(finalPageFirst1, count_page - finalPageFirst1));

        executor.shutdownNow();
    }
}
