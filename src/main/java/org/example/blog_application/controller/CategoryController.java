package org.example.blog_application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blog_application.entity.Category;
import org.example.blog_application.model.requests.CreateCategoryRequest;
import org.example.blog_application.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private  final CategoryService categoryService;

    @GetMapping
    public List<Category> getCategoriesHandler() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public Category createCategoryHandler(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
        return categoryService.createNewCategory(createCategoryRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryHandler(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public Category updateCategoryHandler(@RequestBody @Valid CreateCategoryRequest createCategoryRequest, @PathVariable UUID id) {
        return categoryService.updateCategory(createCategoryRequest, id);
    }
}
