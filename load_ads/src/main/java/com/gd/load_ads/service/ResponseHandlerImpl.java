package com.gd.load_ads.service;

import com.gd.model.dto.Advertising;
import com.gd.model.type.TypeUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ResponseHandlerImpl implements ResponseHandler {
    public Advertising getDetailDescription(Map<String, String> map) {
        final Advertising.AdvertisingBuilder builder = Advertising.builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            switch (entry.getKey()) {
                case "Категория": {
                    builder.category(entry.getValue());
                    break;
                }
                case "Залог, мес.": {
                    builder.pledge(entry.getValue());
                    break;
                }
//                case "Этаж": {
//                    builder.floor(entry.getValue());
//                    break;
//                }
//                case "Этажей в доме": {
//                    builder.countFloorInHouse(entry.getValue());
//                    break;
//                }
//                case "Количество комнат": {
//                    builder.countRoom(entry.getValue());
//                    break;
//                }
//                case "Общая площадь": {
//                    builder.square(entry.getValue());
//                    break;
//                }
                case "Жилая площадь, м²": {
                    builder.livingSpace(entry.getValue());
                    break;
                }
                case "Площадь кухни, м²": {
                    builder.kitchenSquare(entry.getValue());
                    break;
                }
                case "Заголовок": {
                    builder.title(entry.getValue());
                    break;
                }
                case "Цена": {
                    builder.price(Integer.parseInt(entry.getValue().replaceAll("\\D+","")));
                    break;
                }
                case "Адрес": {
                    builder.location(entry.getValue());
                    break;
                }
                case "Тип дома": {
                    builder.typeOfHouse(TypeUtil.houseTypeByString(entry.getValue()));
                    break;
                }
                case "Тип объявления": {
                    builder.adType(TypeUtil.adsTypeByString(entry.getValue()));
                    break;
                }
                case "Срок аренды": {
                    builder.rentalPeriod(TypeUtil.rentalPeriodByString(entry.getValue()));
                    break;
                }
                case "Право собственности": {
                    builder.ownership(TypeUtil.ownershipByString(entry.getValue()));
                    break;
                }
                case "Продавец": {
                    builder.owner(entry.getValue());
                    break;
                }
                case "Агенство": {
                    builder.agency(!entry.getValue().isEmpty());
                    break;
                }
                case "Контактное лицо": {
                    builder.contactPerson(entry.getValue());
                    break;
                }
            }
        }

        return builder.build();
    }
}
