package com.app.restaurant.service;

import com.app.restaurant.data.Ingredient;
import com.app.restaurant.data.Item;
import com.app.restaurant.exception.EntityAlreadyExistsException;
import com.app.restaurant.exception.RestaurantManagerException;
import com.app.restaurant.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;
    private CategoryService categoryService;
    private IngredientService ingredientService;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
            .orElseThrow(() -> new RestaurantManagerException(String.valueOf(id)));
    }

    @SneakyThrows
    public Item save(Item item) {
        if (hasItem(item)) {
            throw new EntityAlreadyExistsException(item.getDescription());
        }

        if (!categoryService.hasCategory(item.getCategory())) {
            categoryService.save(item.getCategory());
        }

        List<Ingredient> ingredientsForTheNewItem = item.getIngredients();
        List<Ingredient> finalIngredientsList = new ArrayList<>();
        ingredientsForTheNewItem.forEach(ingredient -> {
            if (ingredientService.hasIngredient(ingredient)) {
                finalIngredientsList.add(ingredient);
            } else {
                finalIngredientsList.add(ingredientService.save(ingredient));
            }
        });

        item.setIngredients(finalIngredientsList);
        return itemRepository.save(item);
    }

    public void deleteById(Long id) {
        itemRepository.delete(findById(id));
    }

    public boolean hasItem(Item item) {
        return itemRepository.findAll().stream().map(Item::getDescription).filter(description -> item.getDescription().equals(description)).findAny().isPresent();
    }
}
