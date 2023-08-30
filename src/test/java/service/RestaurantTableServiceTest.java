package service;

import com.app.restaurant.data.RestaurantTable;
import com.app.restaurant.repository.RestaurantTableRepository;
import com.app.restaurant.service.RestaurantTableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTableServiceTest {

    @Mock
    private RestaurantTableRepository repository;

    @InjectMocks
    private RestaurantTableService service;

    @Test
    void getAllRestaurantTables() {

        //given
        List<RestaurantTable> tables = new ArrayList<RestaurantTable>();
        RestaurantTable tableOne = new RestaurantTable(1L, "skopje", "1","chair info");
        RestaurantTable tableTwo = new RestaurantTable(2L, "paris", "2","chair info");
        RestaurantTable tableThree = new RestaurantTable(3L, "budapest", "3","chair info");

        tables.add(tableOne);
        tables.add(tableTwo);
        tables.add(tableThree);

        //when
        when(repository.findAll()).thenReturn(tables);
        List<RestaurantTable> actualRestaurantTables = service.getAllRestaurantTables();

        //then
        assertThat(actualRestaurantTables).isEqualTo(tables);
        assertThat(tables.size()).isNotZero();
    }

    @Test
    void findByIdRestaurantTable() {

        //given
        RestaurantTable table = new RestaurantTable(1L, "skopje", "1","chair info");

        //when
        when(repository.findById(1L)).thenReturn(Optional.of(table));
        RestaurantTable actualRestaurantTable = service.findById(1L);

        //then
        assertThat(actualRestaurantTable).isEqualTo(table);
    }

    @Test
    void saveRestaurantTable() {

        //given
        RestaurantTable table = new RestaurantTable(1L, "skopje", "1","chair info");

        service.saveRestaurantTable(table);

        verify(repository,times(1)).save(table);
    }

    @Test
    void deleteRestaurantTable() {

        //given
        RestaurantTable table = new RestaurantTable(1L, "skopje", "1","chair info");

        //when
        when(repository.findById(1L)).thenReturn(Optional.of(table));
        service.deleteRestaurantTable(1L);

        verify(repository,times(1)).delete(table);
    }
}