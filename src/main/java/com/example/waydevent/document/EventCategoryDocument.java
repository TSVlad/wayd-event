package com.example.waydevent.document;

import com.example.waydevent.restapi.dto.EventCategoryDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "categories")
@Data
@NoArgsConstructor
public class EventCategoryDocument {
    @Id
    private String id;
    @Version
    private long version;

    private String categoryName;
    private List<String> subCategories;

    public EventCategoryDocument(EventCategoryDTO eventCategoryDTO) {
        this.id = eventCategoryDTO.getId();
        this.categoryName = eventCategoryDTO.getCategoryName();
        this.subCategories = eventCategoryDTO.getSubCategories();
    }
}
