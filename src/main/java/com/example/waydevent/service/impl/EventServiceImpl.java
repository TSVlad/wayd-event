package com.example.waydevent.service.impl;

import com.example.waydevent.document.EventDocument;
import com.example.waydevent.enums.EventStatus;
import com.example.waydevent.messaging.producer.EventServiceProducer;
import com.example.waydevent.repository.EventRepository;
import com.example.waydevent.restapi.dto.EventDTO;
import com.example.waydevent.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final EventServiceProducer eventServiceProducer;

    @Override
    public Mono<EventDTO> saveEvent(EventDTO eventDTO) {
        EventDocument eventDocument = modelMapper.map(eventDTO, EventDocument.class);
        if (eventDocument.getId() == null) {
            eventDocument.setStatus(EventStatus.CREATED);
            return eventRepository.save(eventDocument)
                    .map(document -> {
                        EventDTO dto = modelMapper.map(document, EventDTO.class);
                        eventServiceProducer.createEvent(dto);
                        return dto;
                    });
        } else {
           return updateEvent(eventDocument);
        }
    }

    private Mono<EventDTO> updateEvent(EventDocument eventDocument) {
        return eventRepository.findById(eventDocument.getId())
                .flatMap(entity -> {
                    eventDocument.setVersion(entity.getVersion());
                    return eventRepository.save(eventDocument);
                }).map(document -> modelMapper.map(document, EventDTO.class));
    }

    @Override
    public Flux<EventDTO> getEventsInPolygon(GeoJsonPolygon polygon) {
        return eventRepository.findAllByPointWithin(polygon)
                .map(document -> modelMapper.map(document, EventDTO.class));
    }

    @Override
    public void updateStatus(String id, EventStatus status) {
        eventRepository.findById(id)
                .doOnNext(eventDocument -> {
                    eventDocument.setStatus(status);
                    eventRepository.save(eventDocument).subscribe();
                })
                .retryWhen(Retry.backoff(50, Duration.ofSeconds(2)).filter(exception -> exception instanceof OptimisticLockingFailureException))
                .subscribe();
    }
}
