package com.example.waydevent.service;

import com.example.waydevent.business.RateEvent;
import com.example.waydevent.config.security.JwtPayload;
import com.example.waydevent.document.EventDocument;
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

    Mono<EventDocument> getEventById(String id);

    Mono<EventDTO> addParticipant(String eventId, JwtPayload userInfo);

    Mono<EventDocument> cancelParticipation(String eventId, JwtPayload userInfo);

    void updateValidity(String id, Validity validity, JwtPayload userInfo);

    Mono<EventDocument> rateEvent(RateEvent rateEvent, long id);
}
