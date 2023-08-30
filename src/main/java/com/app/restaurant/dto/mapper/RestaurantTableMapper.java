package com.app.restaurant.dto.mapper;

import com.app.restaurant.data.*;
import com.app.restaurant.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface RestaurantTableMapper {

    RestaurantTableMapper tableMapperInstance =  Mappers.getMapper(RestaurantTableMapper.class);

    RestaurantTable dtoToEntity(RestaurantTableDto restaurantTableDto);
    RestaurantTableDto toRestaurantTableDto(RestaurantTable restaurantTable);
    List<RestaurantTableDto> toRestaurantTableDtos(List<RestaurantTable> tables);
}
