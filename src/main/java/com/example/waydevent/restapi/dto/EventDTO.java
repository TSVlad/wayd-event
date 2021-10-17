package com.example.waydevent.restapi.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class EventDTO {
    private String id;

    private String name;
    private String description;
    private String contacts;
    private String category;
    private String subCategory;
    private LocalDateTime dateTime;
    private int minNumberOfParticipants;
    private int maxNumberOfParticipants;
    private LocalDateTime deadline;
    private List<String> picturesRefs = new ArrayList<>();
    private GeoJsonPoint point;

    private long ownerId;
    private List<Long> participantsIds = new ArrayList<>();
}
