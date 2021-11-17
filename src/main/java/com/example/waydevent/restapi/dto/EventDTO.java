package com.example.waydevent.restapi.dto;

import com.example.waydevent.enums.EventStatus;
import com.example.waydevent.messaging.consumer.dto.Validity;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class EventDTO {
    private String id;
    private long version;

    private String name;
    private String description;
    private String contacts;
    private String category;
    private String subCategory;
    private ZonedDateTime dateTime;
    private int minNumberOfParticipants;
    private int maxNumberOfParticipants;
    private ZonedDateTime deadline;
    private List<String> picturesRefs = new ArrayList<>();
    private GeoJsonPoint point;
    private int minAge;
    private int maxAge;

    private Validity validity;
    private EventStatus status;

    private long ownerId;
    private Set<Long> participantsIds = new HashSet<>();
}
