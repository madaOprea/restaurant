package com.app.restaurant.controller;

import java.util.*;

import com.app.restaurant.data.TableReservation;
import com.app.restaurant.service.*;
import com.app.restaurant.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AutoConfiguration
@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final DateUtils dateUtils;

    @Autowired
    private final ReservationService reservationService;

    public ReservationController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value="date", required=false) String dateString, Model model){
        Date date = this.dateUtils.createDateFromDateString(dateString);
        List<TableReservation> tables = this.reservationService.getTableReservationsForDate(date);
        model.addAttribute("tableReservations", tables);
        return "tableres";
    }
}
