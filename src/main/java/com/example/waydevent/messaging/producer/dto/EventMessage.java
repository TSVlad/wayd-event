package com.example.waydevent.messaging.producer.dto;

import com.example.waydevent.messaging.AbstractMessage;
import com.example.waydevent.messaging.type.EventMessageType;
import com.example.waydevent.restapi.dto.EventDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventMessage extends AbstractMessage {
    private EventMessageType type;
    private EventDTO eventDTO;
}
