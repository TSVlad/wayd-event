package com.example.waydevent.service.impl;

import com.example.waydevent.document.EventDocument;
import com.example.waydevent.repository.EventRepository;
import com.example.waydevent.restapi.dto.EventDTO;
import com.example.waydevent.service.EventService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;

    @Override
    public Mono<EventDTO> saveEvent(EventDTO eventDTO) {
        EventDocument eventDocument = modelMapper.map(eventDTO, EventDocument.class);
        if (eventDocument.getId() == null) {
            return eventRepository.save(eventDocument)
                    .map(document -> modelMapper.map(document, EventDTO.class));
        } else {
            return eventRepository.findById(eventDocument.getId())
                    .flatMap(e -> {
                        eventDocument.setVersion(e.getVersion());
                        return eventRepository.save(eventDocument);
                    }).map(document -> modelMapper.map(document, EventDTO.class));
        }
    }

    @Override
    public Flux<EventDTO> getEventsInPolygon(GeoJsonPolygon polygon) {
        return eventRepository.findAllByPointWithin(polygon)
                .map(document -> modelMapper.map(document, EventDTO.class));
    }
}
