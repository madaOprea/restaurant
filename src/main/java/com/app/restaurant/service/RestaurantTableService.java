package com.app.restaurant.service;

import com.app.restaurant.data.RestaurantTable;
import com.app.restaurant.exception.EntityAlreadyExistsException;
import com.app.restaurant.exception.RestaurantManagerException;
import com.app.restaurant.repository.RestaurantTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantTableService {

    private RestaurantTableRepository restaurantTableRepository;

    public RestaurantTable saveRestaurantTable(RestaurantTable table) {
        if (hasRestaurantTable(table)) {
            throw new EntityAlreadyExistsException(table.toString());
        }

        return restaurantTableRepository.save(table);
    }

    public void deleteRestaurantTable(Long id) {
        RestaurantTable table = findById(id);
        restaurantTableRepository.delete(table);
    }

    public List<RestaurantTable> getAllRestaurantTables() {
        return restaurantTableRepository.findAll();

    }

    public RestaurantTable findById(Long id) {
        return restaurantTableRepository.findById(id)
                .orElseThrow(() -> new RestaurantManagerException(String.valueOf(id)));
    }

    public boolean hasRestaurantTable(RestaurantTable table) {
        return restaurantTableRepository.findAll().stream().map(RestaurantTable::getTableNumber).filter(tableNo -> table.getTableNumber() == tableNo).findAny().isPresent();
    }
}
