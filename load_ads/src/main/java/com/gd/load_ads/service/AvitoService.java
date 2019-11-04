package com.gd.load_ads.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AvitoService {
    private static final Logger logger =  LogManager.getLogger(AvitoService.class);

    private final ResponseHandler responseHandler;

    private static final String IOS_USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";
    private static final String URL = "https://www.avito.ru";
    private static final String MOBILE_URL = "https://m.avito.ru";
    private static final String URI = "/saratov/kvartiry/sdam";

    public AvitoService(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    public void getInformationAboutAds(int firstPage, int lastPage) throws IOException {
        for (int page = firstPage; page != lastPage; page++) {
            final Document document = Jsoup.connect(URL.concat(URI.concat(String.format("?p=%d", page)))).get();
            Elements newsHeadlines = document.select(".item-description-title");
            for (Element headline : newsHeadlines) {
                final String uri = headline.select("a[href]").attr("href");
                logger.info("------------------------------------------");
                logger.info(uri);
                final Document detailedDescription = Jsoup.connect(MOBILE_URL.concat(uri)).userAgent(IOS_USER_AGENT).get();
                final Map<String, String> mapTitleAndDescription = getMapTitleAndDescription(detailedDescription);
                responseHandler.getDetailDescription(mapTitleAndDescription);
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
        String agency = detailedDescription.select("[data-marker=seller-info/postfix]").text();
        String contact = detailedDescription.select("[data-marker=seller-info/manger]").text();
        map.put("Продавец", owner);
        logger.info("Продавец: " + owner);
        map.put("Агенство", agency);
        logger.info("Агенство: " + agency);
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
