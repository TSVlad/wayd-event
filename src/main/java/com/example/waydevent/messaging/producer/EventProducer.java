package com.example.waydevent.messaging.producer;

import com.example.waydevent.messaging.type.EventMessageType;
import com.example.waydevent.restapi.dto.EventDTO;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventProducer {
    private final KafkaTemplate<Long, EventMessage> kafkaStarshipTemplate;

    public void createEvent(EventDTO eventDTO) {
        send(new EventMessage(EventMessageType.CREATED, eventDTO));
    }

    private void send(EventMessage dto) {
        kafkaStarshipTemplate.send("event-to-orchestrator", dto);
    }
}
