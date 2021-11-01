package com.example.waydevent.restapi.controller;

import com.example.waydevent.config.security.JwtPayload;
import com.example.waydevent.restapi.dto.EventDTO;
import com.example.waydevent.restapi.dto.EventForCreateAndUpdateDTO;
import com.example.waydevent.service.EventService;
import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public Mono<EventDTO> saveEvent(@RequestBody EventForCreateAndUpdateDTO eventDTO, Authentication authentication) {
        JwtPayload principal = (JwtPayload) authentication.getPrincipal();
        long ownerId = principal.getId();
        return eventService.saveEvent(eventDTO, ownerId);
    }

    @PostMapping("/all-in-poly")
    public Flux<EventDTO> getEventsInPolygon(@RequestBody GeoJsonPolygon geoJsonPolygon) {
        return eventService.getEventsInPolygon(geoJsonPolygon);
    }

}
