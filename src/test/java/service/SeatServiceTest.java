package service;

import com.app.restaurant.data.*;
import com.app.restaurant.repository.RestaurantTableRepository;
import com.app.restaurant.repository.SeatRepository;
import com.app.restaurant.service.SeatService;
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
class SeatServiceTest {

    @Mock
    SeatRepository seatRepository;

    @Mock
    RestaurantTableRepository tableRepository;

    @InjectMocks
    SeatService service;

    @Test
    void saveSeat() {
        //given
        RestaurantTable table = new RestaurantTable(1L, "skopje", "1","chair info");
        Seat seat = new Seat(1L, table, 1);
        service.saveSeat(seat);

        verify(seatRepository, times(1)).save(seat);
    }

    @Test
    void deleteSeat() {

        //given
        RestaurantTable table = new RestaurantTable(1L, "skopje", "1","chair info");
        Seat seat = new Seat(1L, table, 1);

        //when
        when(seatRepository.findById(1L)).thenReturn(Optional.of(seat));
        service.deleteSeat(1L);

        verify(seatRepository, times(1)).delete(seat);

    }

    @Test
    void getAllSeats() {

        //when
        RestaurantTable table = new RestaurantTable(1L, "skopje", "1","chair info");
        List<Seat> seats = new ArrayList<Seat>();
        Seat seatOne = new Seat(1L, table, 1);
        Seat seatTwo = new Seat(2L, table, 2);
        Seat seatThree = new Seat(3L, table, 3);

        seats.add(seatOne);
        seats.add(seatTwo);
        seats.add(seatThree);

        //when
        when(seatRepository.findAll()).thenReturn(seats);
        List<Seat> actualSeats = service.getAllSeats();

        //then
        assertThat(actualSeats).isEqualTo(seats);
        assertThat(seats.size()).isNotZero();
    }
}