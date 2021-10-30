package com.example.waydevent.messaging.producer;

import com.example.waydevent.messaging.producer.dto.EventMessage;
import com.example.waydevent.messaging.type.EventMessageType;
import com.example.waydevent.restapi.dto.EventDTO;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventServiceProducer {
    private final KafkaTemplate<Long, EventMessage> kafkaStarshipTemplate;

    public void createEvent(EventDTO eventDTO) {
        send(EventMessage.builder()
                .type(EventMessageType.EVENT_CREATED)
                .eventDTO(eventDTO)
                .build());
    }

    public void updateEvent(EventDTO eventDTO) {
        send(EventMessage.builder()
                .type(EventMessageType.EVENT_UPDATED)
                .eventDTO(eventDTO)
                .build());
    }

    private void send(EventMessage dto) {
        kafkaStarshipTemplate.send("event-topic", dto);
    }
}
