package com.app.restaurant.controller;

import com.app.restaurant.data.*;
import com.app.restaurant.dto.*;
import com.app.restaurant.dto.mapper.RestaurantTableMapper;
import com.app.restaurant.service.RestaurantTableService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant-table")
@RequiredArgsConstructor
@Log4j2
public class RestaurantTableController {

    private final RestaurantTableService restaurantTableService;

    @ApiOperation(value = "get all tables", response = Iterable.class, tags = "getAllTables")
    @GetMapping
    public ResponseEntity<List<RestaurantTableDto>> findAllTables() {
        return ResponseEntity.ok(RestaurantTableMapper.tableMapperInstance.toRestaurantTableDtos(restaurantTableService.getAllRestaurantTables()));
    }

    @ApiOperation(value = "create a new table", response = Iterable.class, tags = "createTable")
    @PostMapping
    public ResponseEntity<RestaurantTableDto> createRestaurantTable(@RequestBody RestaurantTableDto restaurantTableDto) {
        restaurantTableService.saveRestaurantTable(RestaurantTableMapper.tableMapperInstance.dtoToEntity(restaurantTableDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantTableDto);
    }

    @ApiOperation(value = "get specific table", response = Iterable.class, tags = "getTable")
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTableDto> findById(@PathVariable Long id) {
        RestaurantTable restaurantTable = restaurantTableService.findById(id);
        return ResponseEntity.ok(RestaurantTableMapper.tableMapperInstance.toRestaurantTableDto(restaurantTable));
    }

    @ApiOperation(value = "update seat", response = Iterable.class, tags = "updateSeat")
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTableDto> update(@PathVariable Long id, @RequestBody RestaurantTableDto restaurantTableDto) {
        RestaurantTable restaurantTable = RestaurantTableMapper.tableMapperInstance.dtoToEntity(restaurantTableDto);
        restaurantTable.setId(id);

        restaurantTableService.saveRestaurantTable(restaurantTable);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(restaurantTableDto);
    }

    @ApiOperation(value = "delete seat", response = Iterable.class, tags = "deleteSeat")
    @DeleteMapping("/{restaurantTableId}")
    public ResponseEntity<RestaurantTableDto> deleteRestaurantTable(@PathVariable Long restaurantTableId) {
        restaurantTableService.deleteRestaurantTable(restaurantTableId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}