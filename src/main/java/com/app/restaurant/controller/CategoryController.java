package com.app.restaurant.controller;

import com.app.restaurant.data.*;
import com.app.restaurant.dto.*;
import com.app.restaurant.dto.mapper.CategoryMapper;
import com.app.restaurant.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-category")
@RequiredArgsConstructor
@Log4j2
public class CategoryController {

    private final CategoryService categoriesService;

    @ApiOperation(value = "get all categories", response = Iterable.class, tags = "getAllCategories")
    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(CategoryMapper.categoryMapperInstance.toCategoryDtos(categoriesService.findAll()));
    }

    @ApiOperation(value = "create a new category", response = Iterable.class, tags = "createCategory")
    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        categoriesService.save(CategoryMapper.categoryMapperInstance.toCategory(categoryDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }

    @ApiOperation(value = "get category", response = Iterable.class, tags = "getCategory")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        Category category = categoriesService.findById(id);
        return ResponseEntity.ok(CategoryMapper.categoryMapperInstance.toCategoryDto(category));
    }

    @ApiOperation(value = "update category", response = Iterable.class, tags = "updateCategory")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category category = CategoryMapper.categoryMapperInstance.toCategory(categoryDto);
        category.setId(id);

        categoriesService.save(category);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryDto);
    }

    @ApiOperation(value = "delete category", response = Iterable.class, tags = "deleteCategory")
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> delete(@PathVariable Long id) {
        categoriesService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
