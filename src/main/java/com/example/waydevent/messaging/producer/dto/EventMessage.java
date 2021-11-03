package com.example.waydevent.messaging.producer.dto;

import com.example.waydevent.messaging.AbstractMessage;
import com.example.waydevent.messaging.type.EventMessageType;
import com.example.waydevent.restapi.dto.EventDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class EventMessage extends AbstractMessage {
    private EventMessageType type;
    private EventDTO eventDTO;
    private long userId;
}
