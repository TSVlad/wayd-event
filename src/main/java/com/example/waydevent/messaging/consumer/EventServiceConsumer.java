package com.example.waydevent.messaging.consumer;

import com.example.waydevent.enums.EventStatus;
import com.example.waydevent.messaging.consumer.dto.ValidatorMessage;
import com.example.waydevent.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventServiceConsumer {
    private final EventService eventService;

    @KafkaListener(topics = {"validator-to-event"}, containerFactory = "singleFactory")
    public void consume(ValidatorMessage validatorMessage) {
        EventStatus eventStatus;
        if (validatorMessage.isValid()) {
            eventStatus = EventStatus.VALID;
        } else {
            eventStatus = EventStatus.NOT_VALID_BAD_WORD;
        }
        eventService.updateStatus(validatorMessage.getEventId(), eventStatus);
    }
}
