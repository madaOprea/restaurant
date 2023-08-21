package com.app.restaurant.util;

import java.util.Date;
import java.util.List;

import com.app.restaurant.service.*;
import com.app.restaurant.data.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@AutoConfiguration
@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    public AppStartupEvent(ReservationService reservationService, DateUtils dateUtils) {
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Date date = this.dateUtils.createDateFromDateString("2023-01-01");
        List<TableReservation> reservations = this.reservationService.getTableReservationsForDate(date);
        reservations.forEach(System.out::println);
    }
}
