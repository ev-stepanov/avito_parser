package com.gd.model.entity;

import lombok.Data;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;


@Data
@Entity
@Indexed
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field
    private String title;
    @Field
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
