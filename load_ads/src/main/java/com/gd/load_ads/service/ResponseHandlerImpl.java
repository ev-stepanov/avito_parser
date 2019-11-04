package com.gd.load_ads.service;

import com.gd.load_ads.dto.DetailedDescription;
import com.gd.load_ads.type.TypeUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ResponseHandlerImpl implements ResponseHandler {
    public DetailedDescription getDetailDescription(Map<String, String> map) {
        final DetailedDescription.DetailedDescriptionBuilder builder = DetailedDescription.builder();
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
                case "Этаж": {
                    builder.floor(entry.getValue());
                    break;
                }
                case "Этажей в доме": {
                    builder.countFloorInHouse(entry.getValue());
                    break;
                }
                case "Количество комнат": {
                    builder.countRoom(entry.getValue());
                    break;
                }
                case "Общая площадь": {
                    builder.square(entry.getValue());
                    break;
                }
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
                    builder.price(entry.getValue());
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
