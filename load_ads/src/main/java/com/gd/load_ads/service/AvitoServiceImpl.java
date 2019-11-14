package com.gd.load_ads.service;

import com.gd.model.dto.Advertising;
import com.gd.model.util.Converter;
import com.gd.save_ads.service.AdsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AvitoServiceImpl implements AvitoService {
    private static final Logger logger =  LogManager.getLogger(AvitoServiceImpl.class);
    public static final Integer COUNT_PAGE;

    private final AdsService adsService;
    private final ResponseHandler responseHandler;

    private static final String IOS_USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";
    private static final String URL = "https://www.avito.ru";
    private static final String MOBILE_URL = "https://m.avito.ru";
    private static final String URI = "/saratov/kvartiry";
    private static final String DEFAULT_COUNT_OF_PAGES = "1";

    static {
        Document document;
        Elements elements = null;
        try {
            document = Jsoup.connect(URL.concat(URI)).get();
            elements = document.select(".pagination-page");
        } catch (IOException e) {
            logger.error("Failed to determine the number of pages.");
        }
        COUNT_PAGE = Integer.parseInt(
                elements == null ? DEFAULT_COUNT_OF_PAGES : elements
                        .last()
                        .attributes()
                        .asList()
                        .get(1)
                        .getValue()
                        .replaceAll("\\D+", ""));
    }

    public AvitoServiceImpl(ResponseHandler responseHandler, AdsService adsService) {
        this.responseHandler = responseHandler;
        this.adsService = adsService;
    }

    public void getInformationAboutAds(int firstPage, int lastPage) {
        for (int page = firstPage; page < lastPage; page++) {
            final Document document;
            try {
                document = Jsoup.connect(URL.concat(URI.concat(String.format("?p=%d", page)))).get();
            } catch (IOException e) {
                logger.warn(String.format("Failed to read page of number %s", page), e);
                continue;
            }

            Elements newsHeadlines = document.select(".item-description-title");
            for (Element headline : newsHeadlines) {
                final String uri = headline.select("a[href]").attr("href");
                logger.info("---------------------------------------------------------------");
                logger.info(uri);
                Document detailedDescription;
                try {
                    detailedDescription = Jsoup.connect(MOBILE_URL.concat(uri)).userAgent(IOS_USER_AGENT).get();
                } catch (IOException e) {
                    logger.warn(String.format("Failed to read ads:  %s", uri), e);
                    continue;
                }
                final Map<String, String> mapTitleAndDescription = getMapTitleAndDescription(detailedDescription);
                final Advertising advertising = responseHandler.getDetailDescription(mapTitleAndDescription);
                adsService.save(Converter.advertisingToAds(advertising));
            }
        }
    }

    private Map<String, String> getMapTitleAndDescription(Document detailedDescription) {
        final Map<String, String> map = new HashMap<>();
        String titleAds = detailedDescription.select("[data-marker=item-description/title]").text();
        map.put("Заголовок", titleAds);
        logger.info("Title: " + titleAds);

        String price = detailedDescription.select("[data-marker=item-description/price]").text();
        map.put("Цена", price);
        logger.info("Price: " + price);

        String location = detailedDescription.select("[data-marker=delivery/location]").text();
        map.put("Адрес", location);
        logger.info("Location: " + location);

        String owner = detailedDescription.select("[data-marker=seller-info/name]").text();
        map.put("Продавец", owner);
        logger.info("Продавец: " + owner);

        String agency = detailedDescription.select("[data-marker=seller-info/postfix]").text();
        map.put("Агенство", agency);
        logger.info("Агенство: " + agency);

        String contact = detailedDescription.select("[data-marker=seller-info/manger]").text();
        map.put("Контактное лицо", contact);
        logger.info("Контактное лицо, " + contact);


        int i = 0;
        String dataMarkerTitle = String.format("[data-marker=item-properties-item(%d)/title]", i);
        String dataMarkerDescription = String.format("[data-marker=item-properties-item(%d)/description]", i);
        while (!detailedDescription.select(dataMarkerTitle).isEmpty()){
            String title = detailedDescription.select(dataMarkerTitle).text();
            String description = detailedDescription.select(dataMarkerDescription).text();
            map.put(title, description);
            logger.info(String.format("%s: %s", title, description));
            i++;
            dataMarkerTitle = String.format("[data-marker=item-properties-item(%d)/title]", i);
            dataMarkerDescription = String.format("[data-marker=item-properties-item(%d)/description]", i);
        }

        return map;
    }
}
