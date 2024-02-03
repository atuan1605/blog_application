package org.example.blog_application.model.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentRequest {
    private String content;
    private UUID postID;
}
