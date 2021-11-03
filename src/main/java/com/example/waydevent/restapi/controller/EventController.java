package com.example.waydevent.restapi.controller;

import com.example.waydevent.config.security.JwtPayload;
import com.example.waydevent.restapi.dto.EventDTO;
import com.example.waydevent.restapi.dto.EventFilterDTO;
import com.example.waydevent.restapi.dto.EventForCreateAndUpdateDTO;
import com.example.waydevent.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
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

    @PostMapping
    public Mono<EventDTO> saveEvent(@Valid @RequestBody EventForCreateAndUpdateDTO eventDTO, Authentication authentication) {
        JwtPayload principal = (JwtPayload) authentication.getPrincipal();
        long ownerId = principal.getId();
        return eventService.saveEvent(eventDTO, ownerId);
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

    @PostMapping("/participate/{eventId}")
    public Mono<EventDTO> participate(@NotNull @NotEmpty @PathVariable String eventId, Authentication authentication) {
        JwtPayload jwtPayload = (JwtPayload) authentication.getPrincipal();
        return eventService.addParticipant(eventId, jwtPayload);
    }
}
