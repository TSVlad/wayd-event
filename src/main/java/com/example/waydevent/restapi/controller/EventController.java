package com.example.waydevent.restapi.controller;

import com.example.waydevent.business.RateEvent;
import com.example.waydevent.config.security.JwtPayload;
import com.example.waydevent.restapi.dto.EventDTO;
import com.example.waydevent.restapi.dto.EventFilterDTO;
import com.example.waydevent.restapi.dto.EventForCreateAndUpdateDTO;
import com.example.waydevent.restapi.dto.RateEventDTO;
import com.example.waydevent.service.EventService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
    private final ModelMapper modelMapper;

    @PostMapping
    public Mono<EventDTO> saveEvent(@Valid @RequestBody EventForCreateAndUpdateDTO eventDTO, @AuthenticationPrincipal JwtPayload userInfo) {
        return eventService.saveEvent(eventDTO, userInfo);
    }

    @PostMapping("/all-in-poly")
    public Flux<EventDTO> getEventsInPolygon(@Valid @RequestBody EventFilterDTO eventFilterDTO, Authentication authentication) {
        LocalDate dateOfBirth = null;
        if (authentication != null) {
            dateOfBirth = ((JwtPayload) authentication.getPrincipal()).getDateOfBirth();
        }
        return eventService.getEventsInPolygonForFilters(eventFilterDTO, dateOfBirth);
    }

    @GetMapping("/user/{id}")
    public Flux<EventDTO> getEventsForUserId(@PathVariable long id) {
        return eventService.getEventsForUserId(id);
    }

    @PostMapping("/for-ids")
    public Flux<EventDTO> getEventsForIds(@NotNull @RequestBody List<String> ids) {
        return eventService.getEventsForIds(ids);
    }

    @GetMapping("/{id}")
    public Mono<EventDTO> getEventById(@PathVariable String id) { //TODO: secure (check for ACTIVE or owner)
        return eventService.getEventById(id).map(eventDocument -> modelMapper.map(eventDocument, EventDTO.class));
    }

    @PostMapping("/participate/{eventId}")
    public Mono<EventDTO> participate(@NotNull @NotEmpty @PathVariable String eventId, Authentication authentication) {
        JwtPayload jwtPayload = (JwtPayload) authentication.getPrincipal();
        return eventService.addParticipant(eventId, jwtPayload);
    }

    @PostMapping("/rate")
    public Mono<EventDTO> rateEvent(@RequestBody RateEventDTO rateEventDTO, @AuthenticationPrincipal JwtPayload userInfo) {
        return eventService.rateEvent(modelMapper.map(rateEventDTO, RateEvent.class), userInfo.getId())
                .map(eventDocument -> modelMapper.map(eventDocument, EventDTO.class));
    }
}
