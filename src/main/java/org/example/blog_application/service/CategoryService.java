package org.example.blog_application.service;

import lombok.RequiredArgsConstructor;
import org.example.blog_application.entity.Category;
import org.example.blog_application.exception.ItemExistException;
import org.example.blog_application.exception.ResouceNotFoundException;
import org.example.blog_application.model.requests.CreateCategoryRequest;
import org.example.blog_application.repo.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Category createNewCategory(CreateCategoryRequest createCategoryRequest) throws ItemExistException {
        Category existedCategory = categoryRepository.findCategoryByName(createCategoryRequest.getName());
        if (existedCategory == null) {
            throw new ItemExistException("category name is exist: " + createCategoryRequest.getName() );
        }
        Category category = new Category();
        category.setName(createCategoryRequest.getName());
        category.setCreatedAt(new Date());
        categoryRepository.save(category);
        return category;
    }

    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new ResouceNotFoundException("category not found with id = " + id);
        }
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(CreateCategoryRequest createCategoryRequest, UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new ResouceNotFoundException("category not found with id = " + id);
        }
        category.setName(createCategoryRequest.getName());
        return categoryRepository.save(category);
    }
}
