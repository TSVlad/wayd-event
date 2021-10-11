package com.example.waydevent.service;

import com.example.waydevent.restapi.dto.EventDTO;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventService {
    Mono<EventDTO> saveEvent(EventDTO eventDTO);
    Flux<EventDTO> getEventsInPolygon(GeoJsonPolygon polygon);
}
