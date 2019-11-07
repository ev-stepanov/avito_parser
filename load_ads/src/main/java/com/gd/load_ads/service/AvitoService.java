package com.gd.load_ads.service;

import java.io.IOException;

public interface AvitoService {
    void getInformationAboutAds(int firstPage, int lastPage) throws IOException;
}
