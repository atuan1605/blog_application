package org.example.blog_application.service;

import lombok.RequiredArgsConstructor;
import org.example.blog_application.entity.Comment;
import org.example.blog_application.entity.Post;
import org.example.blog_application.entity.User;
import org.example.blog_application.exception.ResouceNotFoundException;
import org.example.blog_application.exception.WrongItemException;
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

    public Comment createNewComment(UUID userID, CommentRequest commentRequest) {
        Comment newComment = new Comment();
        User user = userRepository.findById(userID).orElseThrow(() -> new ResouceNotFoundException("comment not found with id = " + userID));
        Post post = postRepository.findById(commentRequest.getPostID()).orElseThrow(() -> new ResouceNotFoundException("comment not found with id = " + commentRequest.getPostID()));
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

        Post post = postRepository.findById(commentRequest.getPostID()).orElseThrow(() -> new ResouceNotFoundException("comment not found with id = " + commentRequest.getPostID()));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new WrongItemException("wrong post");
        }
        UUID userID = UUID.fromString("test");
        if (!comment.getUser().getId().equals(userID)) {
            throw new WrongItemException("wrong user");
        }

        comment.setContent(commentRequest.getContent());

        return commentRepository.save(comment);
    }

    public void deleteComment(UUID commentID) {
        Comment comment = commentRepository.findById(commentID).orElse(null);
        if (comment == null) {
            throw new ResouceNotFoundException("comment not found with id = " + commentID);
        }

        UUID userID = UUID.fromString("test");
        if (!comment.getUser().getId().equals(userID)) {
            throw new WrongItemException("wrong user");
        }

        commentRepository.deleteById(commentID);
    }
}
