package com.gd.model.dto;

import com.gd.model.type.AdsType;
import com.gd.model.type.HouseType;
import com.gd.model.type.Ownership;
import com.gd.model.type.RentalPeriod;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Advertising {
    private String title;
    private Integer price;
    private String owner;
    private String pledge;
    private Boolean agency;
    private AdsType adType;
    private String location;
    private String category;
    private String livingSpace;
    private Ownership ownership;
    private String contactPerson;
    private String kitchenSquare;
    private HouseType typeOfHouse;
    private RentalPeriod rentalPeriod;
}
