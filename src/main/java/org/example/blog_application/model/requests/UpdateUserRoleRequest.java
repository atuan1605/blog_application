package org.example.blog_application.model.requests;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.blog_application.model.enums.UserRole;

@Data
public class UpdateUserRoleRequest {
    @NotNull(message = "role is require not null")
    private UserRole role;
}
