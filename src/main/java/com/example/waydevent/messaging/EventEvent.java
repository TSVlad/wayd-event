package com.example.waydevent.messaging;

import com.example.waydevent.messaging.type.EventEventType;
import com.example.waydevent.restapi.dto.EventDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EventEvent extends AbstractEvent {
    private EventEventType type;
    private EventDTO eventDTO;

    public EventEvent() {
        super();
    }

    public EventEvent(EventEventType type, EventDTO eventDTO) {
        super();
        this.type = type;
        this.eventDTO = eventDTO;
    }
}
