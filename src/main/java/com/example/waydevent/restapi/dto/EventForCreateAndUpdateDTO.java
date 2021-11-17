package com.example.waydevent.restapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class EventForCreateAndUpdateDTO {
    private String id;

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private String contacts;
    private String category;
    private String subCategory;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateTime;
    private int minNumberOfParticipants;
    private int maxNumberOfParticipants;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime deadline;
    private List<String> picturesRefs = new ArrayList<>();
    @NotNull
    private GeoJsonPoint point;
    private int minAge;
    private int maxAge;
}
