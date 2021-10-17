package com.example.waydevent.messaging.producer;

import com.example.waydevent.messaging.EventEvent;
import com.example.waydevent.messaging.type.EventEventType;
import com.example.waydevent.restapi.dto.EventDTO;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventProducer {
    private final KafkaTemplate<Long, EventEvent> kafkaStarshipTemplate;

    public void createEvent(EventDTO eventDTO) {
        send(new EventEvent(EventEventType.CREATED, eventDTO));
    }

    private void send(EventEvent dto) {
        kafkaStarshipTemplate.send("event-to-orchestrator", dto);
    }
}
