package com.example.waydevent.repository;

import com.example.waydevent.document.EventDocument;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface EventRepository extends ReactiveMongoRepository<EventDocument, String> {

    Flux<EventDocument> findAllByPointWithinAndDateTimeAfter(GeoJsonPolygon geoJsonPolygon, LocalDateTime dateTime);

    Flux<EventDocument> findAllByOwnerId(long id);
}
