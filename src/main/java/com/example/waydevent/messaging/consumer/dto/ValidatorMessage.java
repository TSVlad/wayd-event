package com.example.waydevent.messaging.consumer.dto;

import com.example.waydevent.messaging.AbstractMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidatorMessage extends AbstractMessage {
    private String eventId;
    private boolean isValid;
}
