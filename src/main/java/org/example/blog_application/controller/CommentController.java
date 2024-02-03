package org.example.blog_application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blog_application.entity.Category;
import org.example.blog_application.entity.Comment;
import org.example.blog_application.model.requests.CommentRequest;
import org.example.blog_application.model.requests.CreateCategoryRequest;
import org.example.blog_application.service.CommentService;
import org.hibernate.annotations.Comments;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public Comment createCommentHandler(@RequestBody @Valid CommentRequest commentRequest) {
        UUID userID = UUID.fromString("sadasd");
        return commentService.createNewComment(userID, commentRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentHandler(@PathVariable UUID id) {
        commentService.deleteComment(id);
    }

    @PutMapping("/{id}")
    public Comment updateCommentHandler(@RequestBody @Valid CommentRequest commentRequest, @PathVariable UUID id) {
        return commentService.updateComment(id, commentRequest);
    }
}
