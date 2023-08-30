package com.app.restaurant.dto.mapper;

import com.app.restaurant.data.Item;
import com.app.restaurant.dto.ItemDto;
import com.app.restaurant.dto.mapper.decorator.ItemMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
@DecoratedWith(ItemMapperDecorator.class)
public interface ItemMapper {

    ItemMapper itemMapperInstance =  Mappers.getMapper(ItemMapper.class);

    ItemDto toItemDto(Item item);
    List<ItemDto> toItemDtos(List<Item> items);
    Item toItem(ItemDto itemDto);
}
