package com.fpm.registry.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "place", indexes = @Index(name = "name_index", columnList = "name"))
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 40)
    private String name;

    @Column(name = "canonical_name", length = 100)
    private String canonicalName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @Enumerated(EnumType.STRING)
    private PlaceType type;

    public enum PlaceType {
        AIRPORT,
        AUTONOMOUS_COMMUNITY,
        BOROUGH,
        CANTON,
        CITY,
        CITY_REGION,
        CONGRESSIONAL_DISTRICT,
        COUNTRY,
        COUNTY,
        DEPARTMENT,
        DISTRICT,
        GOVERNORATE,
        MUNICIPALITY,
        NATIONAL_PARK,
        NEIGHBORHOOD,
        OKRUG,
        POSTAL_CODE,
        PREFECTURE,
        PROVINCE,
        REGION,
        STATE,
        TERRITORY,
        TV_REGION,
        UNION_TERRITORY,
        UNIVERSITY
    }
}
