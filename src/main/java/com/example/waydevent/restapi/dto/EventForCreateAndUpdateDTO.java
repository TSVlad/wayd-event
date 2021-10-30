package com.example.waydevent.restapi.dto;

import com.example.waydevent.enums.EventStatus;
import com.example.waydevent.messaging.consumer.dto.Validity;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class EventForCreateAndUpdateDTO {
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
}
