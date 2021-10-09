package com.example.waydevent.restapi.dto;

import com.example.waydevent.document.EventCategoryDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EventCategoryDTO {
    private String id;

    private String categoryName;
    private List<String> subCategories;

    public EventCategoryDTO(EventCategoryDocument eventCategoryDocument) {
        this.id = eventCategoryDocument.getId();
        this.categoryName = eventCategoryDocument.getCategoryName();
        this.subCategories = eventCategoryDocument.getSubCategories();
    }
}
