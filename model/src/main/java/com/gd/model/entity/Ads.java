package com.gd.model.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer price;
    private String owner;
    private String location;
    private String category;
    @Column(name = "contact_person")
    private String contactPerson;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "details")
    private DetailedInformation detailedInformation;

}
