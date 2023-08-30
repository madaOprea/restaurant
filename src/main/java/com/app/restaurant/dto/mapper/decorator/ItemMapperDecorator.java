package com.app.restaurant.dto.mapper.decorator;

import com.app.restaurant.data.*;
import com.app.restaurant.dto.*;
import com.app.restaurant.dto.mapper.ItemMapper;
import com.app.restaurant.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemMapperDecorator implements ItemMapper {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IngredientService ingredientService;

    private ItemMapper mapper;

    public ItemMapperDecorator(ItemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ItemDto toItemDto(Item item) {
        ItemDto itemDto = mapper.toItemDto(item);
        itemDto.setCategory(item.getCategory());
        itemDto.setDescription(item.getDescription());
        itemDto.setType(item.getType());
        itemDto.setPicture(item.getPicture());
        itemDto.setDate(item.getDate());
        itemDto.setIngredients(ingredientService.findAll());

        return itemDto;
    }

    @Override
    public List<ItemDto> toItemDtos(List<Item> items) {
        return mapper.toItemDtos(items);
    }

    @Override
    public Item toItem(ItemDto itemDto) {
        Item item = mapper.toItem(itemDto);
        item.setCategory(itemDto.getCategory());
        item.setDescription(itemDto.getDescription());
        item.setType(itemDto.getType());
        item.setPicture(itemDto.getPicture());
        item.setDate(itemDto.getDate());
        item.setIngredients(itemDto.getIngredients());
        return item;
    }
}
