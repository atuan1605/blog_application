package org.example.blog_application.service;

import lombok.RequiredArgsConstructor;
import org.example.blog_application.entity.User;
import org.example.blog_application.exception.ItemExistException;
import org.example.blog_application.exception.ResouceNotFoundException;
import org.example.blog_application.exception.WrongItemException;
import org.example.blog_application.model.enums.UserRole;
import org.example.blog_application.model.requests.UpdateUserRoleRequest;
import org.example.blog_application.model.requests.UserRequest;
import org.example.blog_application.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createNewUser(UserRequest userRequest) {
        User newUser = new User();

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ItemExistException("username is exist with username: " + userRequest.getUsername());
        }
        if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
            throw new WrongItemException("confirmPassword is incorrect");
        }

        newUser.setUsername(userRequest.getUsername());

        // TODO: Mã hoá password
        newUser.setPasswordHash(userRequest.getPassword());
        newUser.setPhoneNumber(userRequest.getPhoneNumber());
        newUser.setEmail(userRequest.getEmail());

        if (userRequest.getRole() == null) {
            newUser.setRole(UserRole.USER);
        } else {
            newUser.setRole(userRequest.getRole());
        }
        return userRepository.save(newUser);
    }

    public User updateUser(UUID userID, UpdateUserRoleRequest updateUserRoleRequest) {
        User user = userRepository.findById(userID).orElseThrow(() -> new ResouceNotFoundException("comment not found with id = " + userID));
        user.setRole(updateUserRoleRequest.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(UUID userID) {
        UUID targetUserID = UUID.fromString("9004cd10-4fe8-465b-9e9e-e0bd59b61049");
        User adminUser = userRepository.findById(targetUserID).orElseThrow(() -> new ResouceNotFoundException("comment not found with id = " + targetUserID));
        if (adminUser.getRole() != UserRole.ADMIN) {
            throw new WrongItemException("wrong role");
        }

        User deletedUser = userRepository.findById(userID).orElseThrow(() -> new ResouceNotFoundException("comment not found with id = " + userID));
        userRepository.delete(deletedUser);
    }
}
