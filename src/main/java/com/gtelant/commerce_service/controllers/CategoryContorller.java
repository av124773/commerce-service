package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.dtos.CategoryRequest;
import com.gtelant.commerce_service.dtos.CategoryResponse;
import com.gtelant.commerce_service.mappers.CategoryMapper;
import com.gtelant.commerce_service.models.Category;
import com.gtelant.commerce_service.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryContorller {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryContorller(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @Operation(summary = "取得所有Categories列表", description = "")
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories().stream()
                .map(categoryMapper::toResponse)
                .toList()
        );
    }

    @Operation(summary = "建立新的Category", description = "")
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(categoryMapper.toResponse(savedCategory));
    }

    @Operation(summary = "更新指定Categories", description = "")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable int id, @RequestBody CategoryRequest request) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            Category updatedCategory = categoryMapper.updateEntity(category.get(), request);
            Category savedCategory = categoryService.saveCategory(updatedCategory);
            return ResponseEntity.ok(categoryMapper.toResponse(savedCategory));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
