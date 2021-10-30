package com.example.waydevent.restapi.controller;

import com.example.waydevent.restapi.dto.EventDTO;
import com.example.waydevent.restapi.dto.EventForCreateAndUpdateDTO;
import com.example.waydevent.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public Mono<EventDTO> saveEvent(@RequestBody EventForCreateAndUpdateDTO eventDTO) {
        return eventService.saveEvent(eventDTO);
    }

    @PostMapping("/all-in-poly")
    public Flux<EventDTO> getEventsInPolygon(@RequestBody GeoJsonPolygon geoJsonPolygon) {
        return eventService.getEventsInPolygon(geoJsonPolygon);
    }

}
