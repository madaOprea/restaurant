package com.app.restaurant.webservice;

import com.app.restaurant.data.Client;
import com.app.restaurant.data.Reservation;
import com.app.restaurant.data.RestaurantTable;
import com.app.restaurant.service.ReservationService;
import com.app.restaurant.util.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@AutoConfiguration
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    @RequestMapping(path="/reservations", method = GET)
    public List<Reservation> getReservations(@RequestParam(value="date", required = false) String dateString){
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getTableReservationsForDate(date);
    }

    @GetMapping("/clients")
    public List<Client> getClients(){
        return this.reservationService.getRestaurantClients();
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public void addClient(@RequestBody Client client){
        this.reservationService.addClients(client);
    }

    @GetMapping("/tables")
    public List<RestaurantTable> getTables(){
        return this.reservationService.getTables();
    }
}
