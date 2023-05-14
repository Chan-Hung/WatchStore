package com.hungdc.watchstore.controllers;

import com.hungdc.watchstore.dtos.category.CategoryDto;
import com.hungdc.watchstore.entities.Category;
import com.hungdc.watchstore.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable String id) {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody CategoryDto dto) {
        return new ResponseEntity<>(categoryService.create(dto), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable String id, @Valid @RequestBody CategoryDto dto) {
        return new ResponseEntity<>(categoryService.update(id, dto), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable String id) {
        return new ResponseEntity<>(categoryService.delete(id), HttpStatus.OK);
    }
}
