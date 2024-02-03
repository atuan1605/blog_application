package org.example.blog_application.service;

import lombok.RequiredArgsConstructor;
import org.example.blog_application.entity.Category;
import org.example.blog_application.entity.Post;
import org.example.blog_application.entity.User;
import org.example.blog_application.exception.ResouceNotFoundException;
import org.example.blog_application.model.requests.PostRequest;
import org.example.blog_application.repo.CategoryRepository;
import org.example.blog_application.repo.PostRepository;
import org.example.blog_application.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public Post createNewPost(UUID userID, PostRequest postRequest) {
        Post newPost = new Post();
        User user = userRepository.findById(userID).orElseThrow(() -> new ResouceNotFoundException("comment not found with id = " + userID));

         newPost.setTitle(postRequest.getTitle());
         newPost.setContent(postRequest.getContent());
         newPost.setDescription(postRequest.getDescription());
         newPost.setStatus(postRequest.getStatus());

         if (postRequest.getCategoryIDs() != null) {
             List<Category> categoryList = categoryRepository.findAllById(postRequest.getCategoryIDs());
             newPost.setCategoryList(categoryList);
         }
        newPost.setUser(user);

        postRepository.save(newPost);
        return newPost;
    }

    public Post updatePost(UUID postID, PostRequest postRequest) {
        Post post = postRepository.findById(postID).orElseThrow(() -> new ResouceNotFoundException("comment not found with id = " + postID));
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setDescription(postRequest.getDescription());
        post.setStatus(postRequest.getStatus());

        if (postRequest.getCategoryIDs() != null) {
            List<Category> categoryList = categoryRepository.findAllById(postRequest.getCategoryIDs());
            post.setCategoryList(categoryList);
        }
        postRepository.save(post);
        return post;
    }

    public void deletePost(UUID postID) {
        Post post = postRepository.findById(postID).orElseThrow(() -> new ResouceNotFoundException("comment not found with id = " + postID));
        postRepository.delete(post);
    }

    public Post getPostByID(UUID postID) {
        Post post = postRepository.findById(postID).orElseThrow(() -> new ResouceNotFoundException("comment not found with id = " + postID));
        return post;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
