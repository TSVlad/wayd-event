package com.example.waydevent.messaging.consumer;

import com.example.waydevent.enums.EventStatus;
import com.example.waydevent.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidatorConsumer {
    private final EventService eventService;

    @KafkaListener(topics = {"event-validation-to-event"}, containerFactory = "singleFactory")
    public void consume(EventValidationMessage eventValidationMessage) {
        EventStatus eventStatus;
        if (eventValidationMessage.isValid()) {
            eventStatus = EventStatus.VALID;
        } else {
            eventStatus = EventStatus.NOT_VALID_BAD_WORD;
        }
        eventService.updateStatus(eventValidationMessage.getEventId(), eventStatus);
    }
}
