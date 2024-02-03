package org.example.blog_application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blog_application.entity.Category;
import org.example.blog_application.entity.Post;
import org.example.blog_application.model.requests.CreateCategoryRequest;
import org.example.blog_application.model.requests.PostRequest;
import org.example.blog_application.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> getAllPostsHandler() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostDetailsHandler(@PathVariable UUID id) {
        return postService.getPostByID(id);
    }
    @PostMapping
    public Post createNewPostHandler(@RequestBody @Valid PostRequest postRequest) {
        UUID userID = UUID.fromString("9004cd10-4fe8-465b-9e9e-e0bd59b61049");
        return postService.createNewPost(userID, postRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostHandler(@PathVariable UUID id) {
        postService.deletePost(id);
    }

    @PutMapping("/{id}")
    public Post updatePostHandler(@RequestBody @Valid PostRequest postRequest, @PathVariable UUID id) {
        return postService.updatePost(id, postRequest);
    }
}
