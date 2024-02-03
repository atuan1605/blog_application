package org.example.blog_application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blog_application.entity.Post;
import org.example.blog_application.entity.User;
import org.example.blog_application.model.requests.PostRequest;
import org.example.blog_application.model.requests.UpdateUserRoleRequest;
import org.example.blog_application.model.requests.UserRequest;
import org.example.blog_application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createNewUsersHandler(@RequestBody @Valid UserRequest userRequest) {
        return userService.createNewUser(userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserHandler(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUserHandler(@RequestBody @Valid UpdateUserRoleRequest updateUserRoleRequest, @PathVariable UUID id) {
        return userService.updateUser(id, updateUserRoleRequest);
    }
}
