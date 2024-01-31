package org.example.blog_application.service;

import lombok.RequiredArgsConstructor;
import org.example.blog_application.entity.Comment;
import org.example.blog_application.entity.Post;
import org.example.blog_application.entity.User;
import org.example.blog_application.exception.ResouceNotFoundException;
import org.example.blog_application.model.requests.CommentRequest;
import org.example.blog_application.repo.CommentRepository;
import org.example.blog_application.repo.PostRepository;
import org.example.blog_application.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Comment createNewComment(UUID postID, UUID userID, CommentRequest commentRequest) {
        Comment newComment = new Comment();
        User user = userRepository.getReferenceById(userID);
        Post post = postRepository.getReferenceById(postID);
        newComment.setUser(user);
        newComment.setPost(post);
        newComment.setContent(commentRequest.getContent());
        commentRepository.save(newComment);
        return newComment;
    }

    public Comment updateComment(UUID commentID, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(commentID).orElse(null);
        if (comment == null) {
            throw new ResouceNotFoundException("comment not found with id = " + commentID);
        }
        comment.setContent(commentRequest.getContent());
        return commentRepository.save(comment);
    }

    public void deleteComment(UUID commentID) {
        Comment comment = commentRepository.findById(commentID).orElse(null);
        if (comment == null) {
            throw new ResouceNotFoundException("comment not found with id = " + commentID);
        }
        commentRepository.deleteById(commentID);
    }
}
