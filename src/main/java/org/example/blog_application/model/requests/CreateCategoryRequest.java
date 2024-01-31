package org.example.blog_application.model.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    @NotEmpty(message = "name is require not null")
    @NotNull(message = "test")
    private String name;
}
