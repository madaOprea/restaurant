package com.app.restaurant.service;

import com.app.restaurant.data.*;
import com.app.restaurant.exception.*;
import com.app.restaurant.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final RestaurantTableService tableService;

    public Seat saveSeat(Seat seat) {
        if (hasSeat(seat)) {
            throw new EntityAlreadyExistsException(String.valueOf(seat.getSeatNo()));
        }

        return seatRepository.save(seat);
    }

    public void deleteSeat(Long id) {
        Seat seat = findBySeatId(id);
        seatRepository.delete(seat);
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();

    }

    public Seat findBySeatId(Long seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new RestaurantManagerException(this.getClass().getName() + " with given id does not exist."));
    }

    public boolean hasSeat(Seat seat) {
        return seatRepository.findAll().stream().map(Seat::getSeatNo).filter(seatNo -> seat.getSeatNo() == seatNo).findAny().isPresent();
    }
}

