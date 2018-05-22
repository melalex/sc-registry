package com.fpm.registry.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceDto {

    private long id;
    private String name;
    private String canonicalName;
    private CountryDto country;
    private PlaceType type;

    @JsonCreator
    public PlaceDto(String canonicalName) {
        this.canonicalName = canonicalName;
    }

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
