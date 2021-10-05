package com.example.waydevent.db.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "events")
@Data
public class EventDocument {
    @Id
    private String id;
    private String name;
    private String description;
    private String contacts;
    private String category;
    private LocalDateTime dateTime;
    private List<Long> participantsIds;

    private int minNumberOfParticipants;
    private int maxNumberOfParticipants;
    private LocalDateTime deadline;
    private List<String> picturesRefs;
}
