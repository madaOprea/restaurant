package com.app.restaurant.webservice;

import java.util.*;

import com.app.restaurant.service.*;
import com.app.restaurant.data.*;
import com.app.restaurant.data.Table;
import com.app.restaurant.util.*;
import lombok.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@AutoConfiguration
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    @RequestMapping(path="/reservations", method = GET)
    public List<TableReservation> getReservations(@RequestParam(value="date", required = false) String dateString){
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
    public List<Table> getTables(){
        return this.reservationService.getTables();
    }
}
