package com.app.restaurant.dto.mapper.decorator;

import com.app.restaurant.data.RestaurantTable;
import com.app.restaurant.data.Seat;
import com.app.restaurant.dto.SeatDto;
import com.app.restaurant.dto.mapper.SeatMapper;
import com.app.restaurant.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SeatMapperDecorator implements SeatMapper {

    private SeatMapper mapper;

    public SeatMapperDecorator(SeatMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    private RestaurantTableService restaurantTableService;

    @Override
    public Seat toSeat(SeatDto seatDto) {
        Seat seat = mapper.toSeat(seatDto);

        RestaurantTable restaurantTable = restaurantTableService.findById(seatDto.getRestaurantTableId());
        seat.setRestaurantTable(restaurantTable);

        return seat;
    }

    @Override
    public SeatDto toSeatDto(Seat seat) {
        return mapper.toSeatDto(seat);
    }

    @Override
    public List<SeatDto> toSeatDtos(List<Seat> seats) {
        return mapper.toSeatDtos(seats);
    }
}
