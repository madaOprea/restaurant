package com.app.restaurant.dto.mapper;

import com.app.restaurant.data.*;
import com.app.restaurant.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IngredientMapper {

    IngredientMapper ingredientMapperInstance =  Mappers.getMapper(IngredientMapper.class);

    IngredientDto toIngredientDto(Ingredient ingredient);
    List<IngredientDto> toIngredientDtos(List<Ingredient> ingredients);
    Ingredient toIngredient(IngredientDto ingredientDto);
}
