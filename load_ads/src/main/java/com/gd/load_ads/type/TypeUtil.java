package com.gd.load_ads.type;

public class TypeUtil {
    public static HouseType houseTypeByString(String type) {
        switch (type.toLowerCase()) {
            case "кирпичный": {
                return HouseType.BRICK;
            }
            case "панельный": {
                return HouseType.PANEL;
            }
            case "блочный": {
                return HouseType.BLOCK;
            }
            case "монолитный": {
                return HouseType.MONOLITHIC;
            }
            case "деревянный": {
                return HouseType.WOODEN;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public static AdsType adsTypeByString(String type) {
        switch (type.toLowerCase()) {
            case "купить": {
                return AdsType.BUY;
            }
            case "снять": {
                return AdsType.RENT;
            }
            case "продать": {
                return AdsType.BUYERS;
            }
            case "сдать": {
                return AdsType.TENANTS;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public static RentalPeriod rentalPeriodByString(String type) {
        switch (type.toLowerCase()) {
            case "на длительный срок": {
                return RentalPeriod.FOR_LONG_TIME;
            }
            case "посуточно": {
                return RentalPeriod.BY_THE_DAY;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public static Ownership ownershipByString(String type) {
        switch (type.toLowerCase()) {
            case "Посредник": {
                return Ownership.INTERMEDIARY;
            }
            case "Собственник": {
                return Ownership.OWNER;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
}
