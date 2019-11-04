package com.gd.load_ads.dto;

import com.gd.load_ads.type.AdsType;
import com.gd.load_ads.type.HouseType;
import com.gd.load_ads.type.Ownership;
import com.gd.load_ads.type.RentalPeriod;
import lombok.Builder;
import lombok.Getter;

//@Data
@Getter
@Builder
public class DetailedDescription {
    String title;
    String price;
    String floor;
    String pledge;
    String square;
    AdsType adType;
    String location;
    String category;
    String countRoom;
    String livingSpace;
    Ownership ownership;
    String kitchenSquare;
    HouseType typeOfHouse;
    String countFloorInHouse;
    RentalPeriod rentalPeriod;
//
//    public DetailedDescription(Builder builder) {
//        this.title = builder.title;
//        this.price = builder.price;
//        this.location = builder.location;
//        this.category = builder.category;
//        this.adType = builder.adType;
//        this.kitchenSquare = builder.kitchenSquare;
//        this.livingSpace = builder.livingSpace;
//        this.typeOfHouse = builder.typeOfHouse;
//        this.rentalPeriod = builder.rentalPeriod;
//    }
//
//    public static class Builder {
//        String title;
//        String price;
//        String location;
//        String category;
//        AdsType adType;
//        String kitchenSquare;
//        String livingSpace;
//        String typeOfHouse;
//        String rentalPeriod;
//
//        public static Builder newInstance() {
//            return new Builder();
//        }
//
//        public Builder setTitle(String title) {
//            this.title = title;
//            return this;
//        }
//
//        public Builder setPrice(String price) {
//            this.price = price;
//            return this;
//        }
//
//        public Builder setLocation(String location) {
//            this.location = location;
//            return this;
//        }
//
//        public Builder setCategory(String category) {
//            this.category = category;
//            return this;
//        }
//
//        public Builder setAdType(AdsType adType) {
//            this.adType = adType;
//            return this;
//        }
//
//        public Builder setKitchenSquare(String kitchenSquare) {
//            this.kitchenSquare = kitchenSquare;
//            return this;
//        }
//
//        public Builder setLivingSpace(String livingSpace) {
//            this.livingSpace = livingSpace;
//            return this;
//        }
//
//        public Builder setTypeOfHouse(String typeOfHouse) {
//            this.typeOfHouse = typeOfHouse;
//            return this;
//        }
//
//        public Builder setRentalPeriod(String rentalPeriod) {
//            this.rentalPeriod = rentalPeriod;
//            return this;
//        }
//
//        public DetailedDescription build() {
//            return new DetailedDescription(this);
//        }
//    }
}
