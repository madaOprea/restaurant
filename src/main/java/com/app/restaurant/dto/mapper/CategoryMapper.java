package com.app.restaurant.dto.mapper;

import com.app.restaurant.data.Category;
import com.app.restaurant.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper categoryMapperInstance =  Mappers.getMapper(CategoryMapper.class);

    CategoryDto toCategoryDto(Category category);
    List<CategoryDto> toCategoryDtos(List<Category> categories);
    Category toCategory(CategoryDto categoryDto);
}
