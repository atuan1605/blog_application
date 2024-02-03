package org.example.blog_application.model.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PostRequest {
    @NotEmpty(message = "title is require not null")
    private String title;;
    @NotEmpty(message = "content is require not null")
    private String content;
    @NotEmpty(message = "description is require not null")
    private String description;
    @NotNull(message = "status is not null")
    private Boolean status;

    private List<UUID> categoryIDs;
}
