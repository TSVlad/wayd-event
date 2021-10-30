package com.example.waydevent.messaging.producer.dto;

import com.example.waydevent.messaging.AbstractMessage;
import com.example.waydevent.messaging.type.EventMessageType;
import com.example.waydevent.restapi.dto.EventDTO;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class EventMessage extends AbstractMessage {
    private EventMessageType type;
    private EventDTO eventDTO;
}
