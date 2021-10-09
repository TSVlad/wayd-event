package com.example.waydevent.repository;

import com.example.waydevent.document.EventDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EventRepository extends ReactiveMongoRepository<EventDocument, String> {
}
