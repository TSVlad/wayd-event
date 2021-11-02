package com.example.waydevent.service;

import com.example.waydevent.messaging.consumer.dto.Validity;
import com.example.waydevent.restapi.dto.EventDTO;
import com.example.waydevent.restapi.dto.EventFilterDTO;
import com.example.waydevent.restapi.dto.EventForCreateAndUpdateDTO;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface EventService {
    Mono<EventDTO> saveEvent(EventForCreateAndUpdateDTO eventForCreateAndUpdateDTO, long ownerId);
    Flux<EventDTO> getEventsInPolygonForFilters(EventFilterDTO eventFilterDTO, LocalDate finderDateOfBirth);
    void updateValidity(String id, Validity validity);
}
