package com.app.restaurant.controller;

import com.app.restaurant.data.Item;
import com.app.restaurant.dto.ItemDto;
import com.app.restaurant.dto.mapper.ItemMapper;
import com.app.restaurant.service.ItemService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-item")
@RequiredArgsConstructor
@Log4j2
public class ItemController {

    private final ItemService itemsService;

    @ApiOperation(value = "get all items", response = Iterable.class, tags = "getAllItems")
    @GetMapping
    public ResponseEntity<List<ItemDto>> findAll() {
        return ResponseEntity.ok(ItemMapper.itemMapperInstance.toItemDtos(itemsService.findAll()));
    }

    @ApiOperation(value = "get a specific item", response = Iterable.class, tags = "getItem")
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable Long id) {
        Item item = itemsService.findById(id);
        return ResponseEntity.ok(ItemMapper.itemMapperInstance.toItemDto(item));
    }

    @ApiOperation(value = "create a new item", response = Iterable.class, tags = "createItem")
    @PostMapping
    public ResponseEntity<ItemDto> saveItem(@RequestBody ItemDto itemDto) {
        itemsService.save(ItemMapper.itemMapperInstance.toItem(itemDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(itemDto);
    }

    @ApiOperation(value = "delete item", response = Iterable.class, tags = "updateItem")
    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> update(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        Item item = ItemMapper.itemMapperInstance.toItem(itemDto);
        item.setId(id);

        itemsService.save(item);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemDto);
    }

    @ApiOperation(value = "delete item", response = Iterable.class, tags = "deleteItem")
    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDto> delete(@PathVariable Long id) {
        itemsService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
