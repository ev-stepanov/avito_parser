package com.gd.model.util;

import com.gd.model.dto.Advertising;
import com.gd.model.entity.Ads;
import com.gd.model.entity.DetailedInformation;

public class Converter {
    public static Ads advertisingToAds(Advertising advertising) {
        final Ads ads = new Ads();
        ads.setCategory(advertising.getCategory());
        ads.setContactPerson(advertising.getContactPerson());
        ads.setLocation(advertising.getLocation());
        ads.setOwner(advertising.getOwner());
        ads.setPrice(advertising.getPrice());
        ads.setTitle(advertising.getTitle());
        ads.setDetailedInformation(advertisingToDetailedInformation(advertising));
        return ads;
    }

    private static DetailedInformation advertisingToDetailedInformation(Advertising advertising) {
        final DetailedInformation detailedInformation = new DetailedInformation();
        detailedInformation.setAgency(advertising.getAgency());
//        detailedInformation.setCountFloorInHouse(advertising.getCountFloorInHouse());
//        detailedInformation.setCountRoom(advertising.getCountRoom());
//        detailedInformation.setFloor(advertising.getFloor());
        detailedInformation.setKitchenSquare(advertising.getKitchenSquare());
        detailedInformation.setLivingSpace(advertising.getLivingSpace());
        detailedInformation.setPledge(advertising.getPledge());
//        detailedInformation.setSquare(advertising.getSquare());
        detailedInformation.setAdsType(advertising.getAdType());
        detailedInformation.setRentalPeriod(advertising.getRentalPeriod());
        detailedInformation.setHouseType(advertising.getTypeOfHouse());
        detailedInformation.setOwnership(advertising.getOwnership());
        return detailedInformation;
    }
}
