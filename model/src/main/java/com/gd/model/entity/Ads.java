package com.gd.model.entity;

import lombok.Data;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;


@Data
@Entity
@Table(indexes =  {
        @Index(columnList = "details"),
        @Index(columnList = "price")
})
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
    @Field
    private String location;
    private String category;
    @Column(name = "contact_person")
    private String contactPerson;

    @IndexedEmbedded
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "details")
    private DetailedInformation detailedInformation;

}
