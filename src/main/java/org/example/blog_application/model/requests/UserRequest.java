package org.example.blog_application.model.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.example.blog_application.model.enums.UserRole;

@Data
public class UserRequest {
    @NotEmpty(message = "username is require not null")
    private String username;
    @NotEmpty(message = "password is require not null")
    private String password;
    @NotEmpty(message = "confirmPassword is require not null")
    private String confirmPassword;
    private String phoneNumber;
    @NotEmpty(message = "email is require not null")
    private String email;
    private UserRole role;
}
