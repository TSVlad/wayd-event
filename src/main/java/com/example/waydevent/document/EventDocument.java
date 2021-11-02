package com.example.waydevent.document;

import com.example.waydevent.enums.EventStatus;
import com.example.waydevent.messaging.consumer.dto.Validity;
import com.example.waydevent.restapi.dto.EventForCreateAndUpdateDTO;
import com.example.waydevent.util.MappingUtils;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "events")
@Data
public class EventDocument {
    @Id
    private String id;
    @Version
    private long version;

    private String name;
    private String description;
    private String contacts;
    private String category;
    private String subCategory;
    private LocalDateTime dateTime;
    private int minNumberOfParticipants;
    private int maxNumberOfParticipants;
    private LocalDateTime deadline;
    private List<String> picturesRefs = new ArrayList<>();
    private GeoJsonPoint point;
    private int minAge;
    private int maxAge;

    private Validity validity;
    private EventStatus status;

    private long ownerId;
    private List<Long> participantsIds = new ArrayList<>();

    public static EventDocument createEvent(EventForCreateAndUpdateDTO eventForCreateAndUpdateDTO, long ownerId) {
        EventDocument eventDocument = MappingUtils.map(eventForCreateAndUpdateDTO, EventDocument.class);
        eventDocument.setStatus(EventStatus.ON_VALIDATION);
        eventDocument.setValidity(Validity.NOT_VALIDATED);
        eventDocument.setOwnerId(ownerId);
        return eventDocument;
    }

    public void updateEvent(EventForCreateAndUpdateDTO eventForCreateAndUpdateDTO) {
        this.name = eventForCreateAndUpdateDTO.getName();
        this.description = eventForCreateAndUpdateDTO.getDescription();
        this.contacts = eventForCreateAndUpdateDTO.getContacts();
        this.category = eventForCreateAndUpdateDTO.getCategory();
        this.subCategory = eventForCreateAndUpdateDTO.getSubCategory();
        this.dateTime = eventForCreateAndUpdateDTO.getDateTime();
        this.minNumberOfParticipants = eventForCreateAndUpdateDTO.getMinNumberOfParticipants();
        this.maxNumberOfParticipants = eventForCreateAndUpdateDTO.getMaxNumberOfParticipants();
        this.deadline = eventForCreateAndUpdateDTO.getDeadline();
        this.picturesRefs = eventForCreateAndUpdateDTO.getPicturesRefs();
        this.point = eventForCreateAndUpdateDTO.getPoint();

        this.validity = Validity.NOT_VALIDATED;
        this.status = EventStatus.ON_VALIDATION;
    }

    public void updateValidity(Validity validity) {
        this.validity = validity;
        switch (validity) {
            case VALID:
                this.status = EventStatus.ACTIVE;
                break;
            case NOT_VALID:
                this.status = EventStatus.INVALID;
        }
    }
}
