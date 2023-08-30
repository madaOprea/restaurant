package com.app.restaurant.controller;

import com.app.restaurant.data.Reservation;
import com.app.restaurant.service.ReservationService;
import com.app.restaurant.util.DateUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

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

    @ApiOperation(value = "get all reservations", response = Iterable.class, tags = "getAllReservations")
    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value="date", required=false) String dateString, Model model){
        Date date = this.dateUtils.createDateFromDateString(dateString);
        List<Reservation> tables = this.reservationService.getTableReservationsForDate(date);
        model.addAttribute("tableReservations", tables);
        return "tableres";
    }
}
