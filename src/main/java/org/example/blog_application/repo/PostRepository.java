package org.example.blog_application.repo;

import org.example.blog_application.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByStatus(boolean status);
//    @Query(nativeQuery = true, value = "SELECT *")
//    List<Post> findByStatus1(boolean status);
}