package com.app.restaurant.service;

import com.app.restaurant.data.*;
import com.app.restaurant.exception.*;
import com.app.restaurant.repository.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RestaurantManagerException(String.valueOf(id)));
    }

    public Category save(Category category) {
        if (hasCategory(category)) {
            throw new EntityAlreadyExistsException(category.getName());
        }
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.delete(findById(id));
    }

    public boolean hasCategory(Category category) {
        return categoryRepository.findAll().stream().map(Category::getName).filter(description -> category.getName().equals(description)).findAny().isPresent();
    }
}
