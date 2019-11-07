package com.gd.model.entity;

import com.gd.model.type.AdsType;
import com.gd.model.type.HouseType;
import com.gd.model.type.Ownership;
import com.gd.model.type.RentalPeriod;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "detailed_information")
public class DetailedInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String floor;
    private String pledge;
//    private String square;
    private Boolean agency;
//    private String countRoom;
    private String livingSpace;
    private String kitchenSquare;
//    private String countFloorInHouse;

    @JoinColumn(name = "ads_type")
    @Enumerated(EnumType.STRING)
    private AdsType adsType;

    @JoinColumn(name = "house_type")
    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    @JoinColumn(name = "ownership")
    @Enumerated(EnumType.STRING)
    private Ownership ownership;

    @JoinColumn(name = "retail_period")
    @Enumerated(EnumType.STRING)
    private RentalPeriod rentalPeriod;

//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    private Ads ads;
}
