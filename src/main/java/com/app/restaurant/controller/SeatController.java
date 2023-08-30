package com.app.restaurant.controller;

import com.app.restaurant.data.Seat;
import com.app.restaurant.dto.SeatDto;
import com.app.restaurant.dto.mapper.SeatMapper;
import com.app.restaurant.service.SeatService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
@RequiredArgsConstructor
@Log4j2
public class SeatController {

    private final SeatService seatService;

    @ApiOperation(value = "get all seats", response = Iterable.class, tags = "getAllSeats")
    @GetMapping
    public ResponseEntity<List<SeatDto>> findAll() {
        return ResponseEntity.ok(SeatMapper.seatMapperInstance.toSeatDtos(seatService.getAllSeats()));
    }

    @ApiOperation(value = "create a new seat", response = Iterable.class, tags = "createSeat")
    @PostMapping
    public Seat createSeat(@RequestBody SeatDto seatDto) {
        Seat seat = SeatMapper.seatMapperInstance.toSeat(seatDto);
        return seatService.saveSeat(seat);
    }

    @ApiOperation(value = "get specific seat", response = Iterable.class, tags = "getSeat")
    @GetMapping("/{id}")
    public ResponseEntity<SeatDto> findById(@PathVariable Long id) {
        Seat seat = seatService.findBySeatId(id);
        return ResponseEntity.ok(SeatMapper.seatMapperInstance.toSeatDto(seat));
    }

    @ApiOperation(value = "update seat", response = Iterable.class, tags = "updateSeat")
    @PutMapping("/{id}")
    public ResponseEntity<SeatDto> update(@PathVariable Long id, @RequestBody SeatDto seatDto) {
        Seat seat = SeatMapper.seatMapperInstance.toSeat(seatDto);
        seat.setId(id);

        seatService.saveSeat(seat);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(seatDto);
    }

    @ApiOperation(value = "delete seat", response = Iterable.class, tags = "deleteSeat")
    @DeleteMapping("/{seatId}")
    public void deleteSeat(@PathVariable Long seatId) {
        seatService.deleteSeat(seatId);
    }
}

