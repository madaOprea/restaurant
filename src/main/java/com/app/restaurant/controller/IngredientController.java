package com.app.restaurant.controller;

import com.app.restaurant.data.Ingredient;
import com.app.restaurant.dto.IngredientDto;
import com.app.restaurant.dto.mapper.IngredientMapper;
import com.app.restaurant.service.IngredientService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-ingredient")
@RequiredArgsConstructor
@Log4j2
public class IngredientController {

    private final IngredientService ingredientService;

    @ApiOperation(value = "get all ingredients", response = Iterable.class, tags = "getAllIngredients")
    @GetMapping
    public ResponseEntity<List<IngredientDto>> findAll() {
        return ResponseEntity.ok(IngredientMapper.ingredientMapperInstance.toIngredientDtos(ingredientService.findAll()));
    }

    @ApiOperation(value = "create a new ingredient", response = Iterable.class, tags = "createIngredient")
    @PostMapping
    public ResponseEntity<IngredientDto> create(@RequestBody IngredientDto ingredientDto) {
        ingredientService.save(IngredientMapper.ingredientMapperInstance.toIngredient(ingredientDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientDto);
    }

    @ApiOperation(value = "get ingredient", response = Iterable.class, tags = "getIngredient")
    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> findById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.findById(id);
        return ResponseEntity.ok(IngredientMapper.ingredientMapperInstance.toIngredientDto(ingredient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDto> update(@PathVariable Long id, @RequestBody IngredientDto ingredientDto) {
        Ingredient ingredient = IngredientMapper.ingredientMapperInstance.toIngredient(ingredientDto);
        ingredient.setId(id);

        ingredientService.save(ingredient);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ingredientDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IngredientDto> delete(@PathVariable Long id) {
        ingredientService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
