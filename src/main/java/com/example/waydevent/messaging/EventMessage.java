package com.example.waydevent.messaging;

import com.example.waydevent.messaging.type.EventMessageType;
import com.example.waydevent.restapi.dto.EventDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EventMessage extends AbstractMessage {
    private EventMessageType type;
    private EventDTO eventDTO;

    public EventMessage() {
        super();
    }

    public EventMessage(EventMessageType type, EventDTO eventDTO) {
        super();
        this.type = type;
        this.eventDTO = eventDTO;
    }
}
