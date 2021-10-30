package com.example.waydevent.service.impl;

import com.example.waydevent.document.EventDocument;
import com.example.waydevent.messaging.consumer.dto.Validity;
import com.example.waydevent.messaging.producer.EventServiceProducer;
import com.example.waydevent.repository.EventRepository;
import com.example.waydevent.restapi.dto.EventDTO;
import com.example.waydevent.restapi.dto.EventForCreateAndUpdateDTO;
import com.example.waydevent.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final EventServiceProducer eventServiceProducer;

    @Override
    public Mono<EventDTO> saveEvent(EventForCreateAndUpdateDTO eventDTO) {
        if (eventDTO.getId() == null) {
           return createEvent(eventDTO);
        } else {
           return updateEvent(eventDTO);
        }
    }

    private Mono<EventDTO> createEvent(EventForCreateAndUpdateDTO eventForCreateAndUpdateDTO) {
        EventDocument eventDocument = EventDocument.createEvent(eventForCreateAndUpdateDTO);
        return eventRepository.save(eventDocument)
                .map(document -> {
                    EventDTO dto = modelMapper.map(document, EventDTO.class);
                    eventServiceProducer.createEvent(dto);
                    return dto;
                });
    }

    private Mono<EventDTO> updateEvent(EventForCreateAndUpdateDTO eventForCreateAndUpdateDTO) {
        return eventRepository.findById(eventForCreateAndUpdateDTO.getId())
                .flatMap(eventDocument -> {
                    eventDocument.updateEvent(eventForCreateAndUpdateDTO);
                    return eventRepository.save(eventDocument);
                }).map(document -> {
                    EventDTO eventDTO = modelMapper.map(document, EventDTO.class);
                    eventServiceProducer.updateEvent(eventDTO);
                    return eventDTO;
                });
    }

    @Override
    public Flux<EventDTO> getEventsInPolygon(GeoJsonPolygon polygon) {
        return eventRepository.findAllByPointWithin(polygon)
                .map(document -> modelMapper.map(document, EventDTO.class));
    }

    @Override
    public void updateValidity(String id, Validity validity) {
        eventRepository.findById(id)
                .doOnNext(eventDocument -> {
                    eventDocument.updateValidity(validity);
                    eventRepository.save(eventDocument).subscribe();
                }).subscribe();
    }
}
