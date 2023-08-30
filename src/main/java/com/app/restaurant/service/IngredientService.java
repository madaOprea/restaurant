package com.app.restaurant.service;

import com.app.restaurant.data.Ingredient;
import com.app.restaurant.exception.*;
import com.app.restaurant.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngredientService {

    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new RestaurantManagerException(String.valueOf(id)));
    }

    public Ingredient save(Ingredient ingredient) {
        if (hasIngredient(ingredient)) {
            throw new EntityAlreadyExistsException(ingredient.getName());
        }
        return ingredientRepository.save(ingredient);
    }

    public void deleteById(Long id) {
        ingredientRepository.delete(findById(id));
    }

    public List<Ingredient> findAllByIds(List<Long> ids) {
        List<Ingredient> ingredients = new ArrayList<>();
        ids.forEach(id -> {Ingredient ingredient = findById(id);
            ingredients.add(ingredient);
        });
        return ingredients;
    }

    public boolean hasIngredient(Ingredient ingredient) {
        return ingredientRepository.findAll().stream().map(Ingredient::getName).filter(name -> ingredient.getName().equals(name)).findAny().isPresent();
    }

    public List<Long> findIdsForIngredients(){
        return ingredientRepository.findAll().stream().map(Ingredient::getId).collect(Collectors.toList());
    }
}
