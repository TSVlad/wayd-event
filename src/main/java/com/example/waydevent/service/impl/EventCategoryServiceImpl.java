package com.example.waydevent.service.impl;

import com.example.waydevent.document.EventCategoryDocument;
import com.example.waydevent.repository.EventCategoryRepository;
import com.example.waydevent.restapi.dto.EventCategoryDTO;
import com.example.waydevent.service.EventCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EventCategoryServiceImpl implements EventCategoryService {

    private EventCategoryRepository eventCategoryRepository;

    @Override
    public Flux<EventCategoryDTO> getAllEventCategories() {
        return eventCategoryRepository.findAll().map(EventCategoryDTO::new);
    }

    @Override
    public Mono<EventCategoryDTO> saveEventCategory(EventCategoryDTO eventCategoryDTO) {
        if (eventCategoryDTO.getId() == null) {
            return eventCategoryRepository.save(new EventCategoryDocument(eventCategoryDTO))
                    .map(EventCategoryDTO::new);
        } else {
            return  eventCategoryRepository.findById(eventCategoryDTO.getId())
                    .flatMap(ec -> {
                        EventCategoryDocument document = new EventCategoryDocument(eventCategoryDTO);
                        document.setVersion(ec.getVersion());
                        return eventCategoryRepository.save(document);
                    })
                    .map(EventCategoryDTO::new);
        }
    }

    @Override
    public Mono<Void> deleteEventCategory(String id) {
        return eventCategoryRepository.deleteById(id);
    }
}
