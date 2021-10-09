package com.example.waydevent.restapi.controller;

import com.example.waydevent.restapi.dto.EventCategoryDTO;
import com.example.waydevent.service.EventCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/event-category")
@AllArgsConstructor
@Slf4j
public class EventCategoryController {
    private EventCategoryService eventCategoryService;

    @GetMapping()
    public Flux<EventCategoryDTO> getAllEventCategories(Authentication authentication) {
        return eventCategoryService.getAllEventCategories();
    }

    @PostMapping()
    public Mono<EventCategoryDTO> saveEventCategory(@RequestBody EventCategoryDTO eventCategoryDTO) {
        return eventCategoryService.saveEventCategory(eventCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCategory(@PathVariable String id) {
        return eventCategoryService.deleteEventCategory(id);
    }
}
