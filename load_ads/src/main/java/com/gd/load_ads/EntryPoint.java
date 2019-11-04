package com.gd.load_ads;

import com.gd.load_ads.service.AvitoService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class EntryPoint {
    private final AvitoService avitoService;

    public EntryPoint(AvitoService avitoService) {
        this.avitoService = avitoService;
    }

    @PostConstruct
    public void main() throws IOException {
        avitoService.getInformationAboutAds();
    }
}
