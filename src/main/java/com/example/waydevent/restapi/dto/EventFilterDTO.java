package com.example.waydevent.restapi.dto;

import jdk.jfr.Category;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.time.LocalDate;

@Data
public class EventFilterDTO {
    String category;
    String subcategory;
    LocalDate dateAfter;
    LocalDate dateBefore;

    GeoJsonPolygon geoJsonPolygon;
}
