package com.app.restaurant.controller;

import com.app.restaurant.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AutoConfiguration
@AllArgsConstructor
@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private final ReservationService reservationService;

    @ApiOperation(value = "get all clients", response = Iterable.class, tags = "getAllClients")
    @RequestMapping(method = RequestMethod.GET)
    public String getClients(Model model){
        model.addAttribute("clients", this.reservationService.getRestaurantClients());
        return "restaurant-guests";
    }
}

