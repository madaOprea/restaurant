package com.app.restaurant.dto.mapper;

import com.app.restaurant.data.Seat;
import com.app.restaurant.dto.SeatDto;
import com.app.restaurant.dto.mapper.decorator.SeatMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
@DecoratedWith(SeatMapperDecorator.class)
public interface SeatMapper {

    static SeatMapper seatMapperInstance =  Mappers.getMapper(SeatMapper.class);

    Seat toSeat(SeatDto seatDto);
    SeatDto toSeatDto(Seat seat);
    List<SeatDto> toSeatDtos(List<Seat> seats);
}
