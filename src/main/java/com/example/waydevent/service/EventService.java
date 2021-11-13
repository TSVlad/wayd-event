package com.example.waydevent.service;

import com.example.waydevent.config.security.JwtPayload;
import com.example.waydevent.messaging.consumer.dto.Validity;
import com.example.waydevent.restapi.dto.EventDTO;
import com.example.waydevent.restapi.dto.EventFilterDTO;
import com.example.waydevent.restapi.dto.EventForCreateAndUpdateDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    Mono<EventDTO> saveEvent(EventForCreateAndUpdateDTO eventForCreateAndUpdateDTO, JwtPayload userInfo);

    Flux<EventDTO> getEventsInPolygonForFilters(EventFilterDTO eventFilterDTO, LocalDate finderDateOfBirth);

    Flux<EventDTO> getEventsForUserId(long id);

    Flux<EventDTO> getEventsForIds(List<String> ids);

    Mono<EventDTO> addParticipant(String eventId, JwtPayload userInfo);

    void updateValidity(String id, Validity validity, JwtPayload userInfo);
}
