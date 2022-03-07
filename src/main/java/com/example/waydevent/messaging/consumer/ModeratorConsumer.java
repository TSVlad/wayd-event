package com.example.waydevent.messaging.consumer;

import com.example.waydevent.messaging.consumer.msg.ModerationMessage;
import com.example.waydevent.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ModeratorConsumer {
    private final EventService eventService;

    @KafkaListener(topics = {"moderation-to-event"}, containerFactory = "singleFactory")
    public void consume(ModerationMessage message) {
        switch (message.getType()) {
            case BLOCK_EVENT:
                eventService.blockDocument(message.getObjectId()).subscribe();
                break;
        }
    }
}
