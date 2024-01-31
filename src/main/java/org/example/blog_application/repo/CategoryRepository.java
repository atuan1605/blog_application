package org.example.blog_application.repo;

import org.example.blog_application.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category findCategoryByName(String name);
}